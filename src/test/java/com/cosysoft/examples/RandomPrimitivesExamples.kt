package com.cosysoft.examples

import com.cosysoft.randprim.RandomPrimitivesImpl
import com.cosysoft.randprim.TraceAlternativeBuilder
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import java.util.function.Supplier

/**
 * todo: description
 */
@Test
class RandomPrimitivesExamples {

    /**
     * Example of correct usage [RandomPrimitivesImpl.getRandomAnyPossibleInt].
     */
    @Test
    fun `example of correct usage getRandomAnyPossibleInt`() {
        val rand = RandomPrimitivesImpl()
        val value: Int = rand.randomAnyPossibleInt.next()
        Assert.assertTrue(value >= Int.MIN_VALUE)
        Assert.assertTrue(value <= Int.MAX_VALUE)
        value
    }

    /**
     * Example of correct usage [RandomPrimitivesImpl.getRandomAbsInt].
     */
    @Test
    fun `Example of correct usage getRandomAbsInt`() {
        val rand = RandomPrimitivesImpl()
        val value: Int = rand.randomAbsInt.next()
        Assert.assertTrue(value >= 0)
        Assert.assertTrue(value <= Int.MAX_VALUE)
    }

    /**
     * Example of correct usage [RandomPrimitivesImpl.getRandomAbsIntFrom].
     */
    @Test
    fun `Example of correct usage getRandomAbsIntFrom`() {
        val rand = RandomPrimitivesImpl()
        val min = Int.MAX_VALUE - 4
        val randomAbsIntFrom = rand.getRandomAbsIntFrom(min).next()
        Assert.assertTrue(randomAbsIntFrom >= min)
        Assert.assertTrue(randomAbsIntFrom <= Int.MAX_VALUE)

    }

    /**
     * Example of correct usage [RandomPrimitivesImpl.getRandomAbsIntTo].
     */
    @Test
    fun `Example of correct usage getRandomAbsIntTo`() {
        val rand = RandomPrimitivesImpl()
        val max = 10
        val randomAbsIntTo = rand.getRandomAbsIntTo(max).next()
        Assert.assertTrue(randomAbsIntTo >= 0)
        Assert.assertTrue(randomAbsIntTo < max)
    }

    /**
     * Example of correct usage [RandomPrimitivesImpl.getRandomAbsIntFromTo].
     */
    @Test
    fun `Example of correct usage getRandomAbsIntFromTo`() {
        val rand = RandomPrimitivesImpl()
        val min = 100
        val max = 1000
        val randomAbsIntFromTo = rand.getRandomAbsIntFromTo(min, max).next()
        Assert.assertTrue(randomAbsIntFromTo >= min)
        Assert.assertTrue(randomAbsIntFromTo < max)
    }

    /**
     * Example of correct invocations of various random-int-methods with alternatives.
     * @param rand instance of [RandomPrimitivesImpl].
     * @param randMethod lambda that invoke one of [rand]'s method for random [Int].
     */
    @Test(dataProvider = "randomIntMethods")
    fun `Example of int TraceAlternativeBuilder usages with alternatives`(
            rand: RandomPrimitivesImpl,
            randMethod: () -> TraceAlternativeBuilder<Int, Int>)
    {
        val builder = randMethod()
                .or(-1)
                .or(-1)
                .or(-2, -3)
                .or { -4 }
                .or { null }
                .orNull()
                .orResult(
                        Supplier { -5 },
                        Supplier { -6 }
                )
        val value1 = builder.next()
        val value2 = builder.next()
        val value3 = builder.next()
        val value4 = builder.next()
        val value5 = builder.next()
        val value6 = builder.next()
        val value7 = builder.next()
        val value8 = builder.next()
        val value9 = builder.next()
        val value10 = builder.next()

        builder.traceAs("my label")
        val value11 = builder.next()
        val value12 = builder.next()
    }

    /**
     * Example of correct invocations of various random-int-methods without tracing.
     * @param rand instance of [RandomPrimitivesImpl].
     * @param randMethod lambda that invoke one of [rand]'s method for random [Int].
     */
    @Test(dataProvider = "randomIntMethods")
    fun `Example of int TraceAlternativeBuilder usages without tracing`(
            rand: RandomPrimitivesImpl,
            randMethod: () -> TraceAlternativeBuilder<Int, Int>)
    {
        val randomIntThatWillNotBeTraced: Int = randMethod()
                .isTraceIt(false)
                .traceAs("actually, that value will not be traced, and you'll never see this message in report")
                .next()
        Assert.assertNotNull(randomIntThatWillNotBeTraced)
        val tracingReport = rand.tracingReport
    }

    /**
     * Example of correct invocations of various random-int-methods with tracing (without tracing label).
     * @param rand instance of [RandomPrimitivesImpl].
     * @param randMethod lambda that invoke one of [rand]'s method for random [Int].
     */
    @Test(dataProvider = "randomIntMethods")
    fun `Example of int TraceAlternativeBuilder usages with tracing without label`(
            rand: RandomPrimitivesImpl,
            randMethod: () -> TraceAlternativeBuilder<Int, Int>)
    {
        val randomIntWithoutLabel: Int = randMethod().next()
        Assert.assertNotNull(randomIntWithoutLabel)
        val tracingReport = rand.tracingReport
    }

    /**
     * Example of correct invocations of various random-int-methods with tracing (with tracing label).
     * @param rand instance of [RandomPrimitivesImpl].
     * @param randMethod lambda that invoke one of [rand]'s method for random [Int].
     */
    @Test(dataProvider = "randomIntMethods")
    fun `Example of int TraceAlternativeBuilder usages with tracing with label`(
            rand: RandomPrimitivesImpl,
            randMethod: () -> TraceAlternativeBuilder<Int, Int>)
    {
        val randomInt: Int = randMethod()
                .traceAs("randomInt")
                .next()
        val tracedValueRandomInt = rand.getTracedValueOrDie("randomInt", Integer::class.java)
        Assert.assertEquals(randomInt, tracedValueRandomInt)
        Assert.assertNotNull(randomInt)
        val tracingReport = rand.tracingReport
    }

    /**
     * Example of correct invocations of various random-int-methods with alternatives and tracing (with tracing label).
     * @param rand instance of [RandomPrimitivesImpl].
     * @param randMethod lambda that invoke one of [rand]'s method for random [Int].
     */
    @Test(dataProvider = "randomIntMethods")
    fun `Example of int TraceAlternativeBuilder usages with alternatives and tracing`(
            rand: RandomPrimitivesImpl,
            randMethod: () -> TraceAlternativeBuilder<Int, Int>)
    {
        val randomIntWithAlternative: Int = randMethod()
                .or(-1)
                .traceAs("randomInt with alternatives")
                .next()
        val tracedValueRandomIntWithAlternatives =
                rand.getTracedValue("randomInt with alternatives", Integer::class.java)
        Assert.assertEquals(randomIntWithAlternative, tracedValueRandomIntWithAlternatives.get())
        Assert.assertNotNull(randomIntWithAlternative)
        val tracingReport = rand.tracingReport
    }

    /**
     * @return instance of [RandomPrimitivesImpl] and lambda that invoke one of instance's method for random [Int].
     */
    @DataProvider(name = "randomIntMethods")
    fun randomIntMethods(): Array<Array<Any>> {
        val rand1 = RandomPrimitivesImpl()
        val rand2 = RandomPrimitivesImpl()
        val rand3 = RandomPrimitivesImpl()
        val rand4 = RandomPrimitivesImpl()
        val rand5 = RandomPrimitivesImpl()
        return arrayOf(
                arrayOf<Any>(
                        rand1,
                        {
                            rand1.getRandomAnyPossibleInt()
                        }
                ),
                arrayOf<Any>(
                        rand2,
                        {
                            rand2.getRandomAbsInt()
                        }
                ),
                arrayOf<Any>(
                        rand3,
                        {
                            val min = Int.MAX_VALUE - 4;
                            rand3.getRandomAbsIntFrom(min)
                        }
                ),
                arrayOf<Any>(
                        rand4,
                        {
                            val max = 10
                            rand4.getRandomAbsIntTo(max)
                        }
                ),
                arrayOf<Any>(
                        rand5,
                        {
                            val min = 100
                            val max = 1000
                            rand5.getRandomAbsIntFromTo(min, max)
                        }
                )
        )
    }
}
