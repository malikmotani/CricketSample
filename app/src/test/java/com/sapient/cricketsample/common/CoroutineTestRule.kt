package com.sapient.cricketsample.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * During the unit-test, it enables the main dispatcher to use TestCoroutineDispatcher.
 * After the test, it resets and cleanup.
 */
@ExperimentalCoroutinesApi
class CoroutineTestRule : TestRule {

    val scheduler = TestCoroutineScheduler()

    private val testCoroutineDispatcher = StandardTestDispatcher(scheduler)

    override fun apply(statement: Statement, description: Description?) = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)
            statement.evaluate()
            Dispatchers.resetMain()
        }
    }

}