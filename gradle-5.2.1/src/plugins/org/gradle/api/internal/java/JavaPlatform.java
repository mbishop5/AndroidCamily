/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.api.internal.java;

import com.google.common.collect.ImmutableSet;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.PublishArtifact;
import org.gradle.api.attributes.AttributeContainer;
import org.gradle.api.attributes.Usage;
import org.gradle.api.internal.artifacts.dsl.dependencies.PlatformSupport;
import org.gradle.api.internal.attributes.AttributeContainerInternal;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.component.SoftwareComponentInternal;
import org.gradle.api.internal.component.UsageContext;
import org.gradle.api.internal.java.usagecontext.ConfigurationUsageContext;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.plugins.JavaPlatformPlugin;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Set;

public class JavaPlatform implements SoftwareComponentInternal {
    private final ObjectFactory objectFactory;
    private final ImmutableAttributesFactory attributesFactory;
    private final ConfigurationContainer configurations;
    private final UsageContext api;
    private final UsageContext runtime;

    @Inject
    public JavaPlatform(ObjectFactory objectFactory, ImmutableAttributesFactory attributesFactory, ConfigurationContainer configurations) {
        this.objectFactory = objectFactory;
        this.attributesFactory = attributesFactory;
        this.configurations = configurations;
        this.api = createApiUsageContext();
        this.runtime = createRuntimeUsageContext();
    }

    @Override
    public Set<? extends UsageContext> getUsages() {
        return ImmutableSet.of(api, runtime);
    }

    @Override
    public String getName() {
        return "javaPlatform";
    }

    private UsageContext createRuntimeUsageContext() {
        return new JavaPlatformUsageContext(Usage.JAVA_RUNTIME, "runtime", JavaPlatformPlugin.RUNTIME_ELEMENTS_CONFIGURATION_NAME, configurations, objectFactory, attributesFactory);
    }

    private UsageContext createApiUsageContext() {
        return new JavaPlatformUsageContext(Usage.JAVA_API, "api", JavaPlatformPlugin.API_ELEMENTS_CONFIGURATION_NAME, configurations, objectFactory, attributesFactory);
    }

    private final static class JavaPlatformUsageContext extends ConfigurationUsageContext {
        private final AttributeContainer attributes;

        private JavaPlatformUsageContext(String usageName,
                                         String name,
                                         String configurationName,
                                         ConfigurationContainer configurations,
                                         ObjectFactory objectFactory,
                                         ImmutableAttributesFactory attributesFactory) {
            super(usageName, name, configurationName, Collections.<PublishArtifact>emptySet(), configurations, objectFactory, attributesFactory);
            AttributeContainerInternal attributes = (AttributeContainerInternal) super.getAttributes();
            // Currently, "enforced" platforms are handled through special casing, so we don't need to publish the enforced version
            this.attributes = attributesFactory.concat(attributes.asImmutable(), PlatformSupport.COMPONENT_CATEGORY, PlatformSupport.REGULAR_PLATFORM);
        }

        @Override
        public AttributeContainer getAttributes() {
            return attributes;
        }
    }
}
