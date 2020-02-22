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

package org.gradle.api.internal.tasks.execution;

import org.gradle.api.internal.TaskInternal;
import org.gradle.api.internal.tasks.TaskExecuter;
import org.gradle.api.internal.tasks.TaskExecuterResult;
import org.gradle.api.internal.tasks.TaskExecutionContext;
import org.gradle.api.internal.tasks.TaskStateInternal;
import org.gradle.caching.internal.tasks.TaskOutputCachingBuildCacheKey;
import org.gradle.internal.operations.ExecutingBuildOperation;

import java.util.function.Consumer;

/**
 * {@inheritDoc}
 */
public class FinishSnapshotTaskInputsBuildOperationTaskExecuter implements SnapshotTaskInputsMeasuringTaskExecuter {
    private final TaskExecuter delegate;

    public FinishSnapshotTaskInputsBuildOperationTaskExecuter(
        TaskExecuter delegate
    ) {
        this.delegate = delegate;
    }

    @Override
    public TaskExecuterResult execute(TaskInternal task, TaskStateInternal state, final TaskExecutionContext context) {
        context.removeSnapshotTaskInputsBuildOperation()
            .ifPresent(new Consumer<ExecutingBuildOperation>() {
                @Override
                public void accept(ExecutingBuildOperation operation) {
                    TaskOutputCachingBuildCacheKey cacheKey = context.getBuildCacheKey();
                    operation.setResult(new OperationResultImpl(cacheKey));
                }
            });
        return delegate.execute(task, state, context);
    }
}
