package com.vladsonkin.stolenbikesnl.domain.executor

import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the ThreadExecutor
 */
interface ThreadExecutor : Executor