package com.example.accessibilitymobilemasterclass

import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.accessibilitymobilemasterclass.ui.theme.AccessibilityMobileMasterClassTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.accessibilitymobilemasterclass", appContext.packageName)
    }

    @ExperimentalComposeUiApi
    @Test
    fun checkSomething(){
        composeTestRule.setContent {
            AccessibilityMobileMasterClassTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    NextScreen()
                }
            }
        }
        composeTestRule.onNodeWithText("First field").assertIsDisplayed()
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("unmerged")
        composeTestRule.onRoot(useUnmergedTree = false).printToLog("merged")
    }
}