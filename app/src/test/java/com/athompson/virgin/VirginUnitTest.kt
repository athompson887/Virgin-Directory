package com.athompson.virgin

import com.athompson.virgin.application.VirginDirectoryApplication
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class VirginUnitTest {

    @Test
    fun testApplicationFunctionShouldFail() {
        val app = VirginDirectoryApplication()
        val result = app.test()
        assertEquals("test", result)//should fail
    }

    @Test
    fun testApplicationFunction() {
        val app = VirginDirectoryApplication()
        val result = app.test()
        assertEquals("Test works", result)//should pass
    }

    @Test
    fun testDataFormatShouldPass(){
        val result = "2022-01-24T17:02:23.729Z".formatDateString()
        assertEquals("24/01/2022",result)
    }

    @Test
    fun testDataFormatShouldFail(){
        val result = "2022-01-24T17:02:23.729Z".formatDateString()
        assertNotEquals("24/01:2022",result)
    }

    // This initially failed with
    // Unparseable date: "This is a load of tripe"
    // java.text.ParseException: Unparseable date: "This is a load of tripe"
    // I fixed it by catching exception
    @Test
    fun testDataFormatIncorrectFormat(){
        val result = "This is a load of tripe".formatDateString()
        assertEquals("",result)
    }

    @Test
    fun testDataFormatEmptyString(){
        val result = "".formatDateString()
        assertEquals("",result)
    }
}