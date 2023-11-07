package com.example.accessibilitymobilemasterclass

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accessibilitymobilemasterclass.ui.theme.AccessibilityMobileMasterClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccessibilityMobileMasterClassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var firstScreen by remember { mutableStateOf(true) }
                    if(firstScreen) {
                        Greeting("Android", onExit = {
                            firstScreen = false
                        })
                    } else {
                        NextScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onExit: () -> Unit) {
    val (first, second, third, fourth, fifth, sixth, seventh) = remember { FocusRequester.createRefs() }
    Column {
        Row {
            TextButton(
                {},
                Modifier
                    .focusRequester(first)
                    .focusProperties { next = second }
            ) {
                Text("First field")
            }
            TextButton(
                {},
                Modifier
                    .focusRequester(third)
                    .focusProperties { next = fourth }
            ) {
                Text("Third field")
            }
        }

        Row {
            TextButton(
                {},
                Modifier
                    .focusRequester(second)
                    .focusProperties { next = third }
            ) {
                Text("Second field")
            }
            TextButton(
                {},
                Modifier
                    .focusRequester(fourth)
                    .focusProperties { next = fifth }
            ) {
                Text("Fourth field")
            }
        }
        Button(onClick = { },
            modifier =
            Modifier
                .focusRequester(fifth)
                .focusProperties { next = sixth }
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "heart icon"
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Like")
        }

        Button(
            onClick = { /* Handle button click */ },
            modifier =
            Modifier
                .focusRequester(sixth)
                .focusProperties { next = seventh }
                .semantics {
                    contentDescription = "Search the list"
                    role = Role.Checkbox
                }
        ) {
            Text(text = "Search", modifier = Modifier.clearAndSetSemantics { })
        }
        Button(
            onClick = onExit,
            modifier =
            Modifier
                .focusRequester(seventh)
                .focusProperties { next = first }
                .semantics {
                    contentDescription = "Navigate to the next screen"
                    role = Role.Button
                }
        ) {
            Text(text = "Next screen plsss", modifier = Modifier.clearAndSetSemantics { })
        }
    }
}

@Composable
fun NextScreen(){
    Column {
        var checked by remember { mutableStateOf(false) }
        val stateSubscribed = stringResource(R.string.subscribed)
        val stateNotSubscribed = stringResource(R.string.unsubscribed)
        Row(
            Modifier
                .toggleable(
                    value = checked,
                    role = Role.Switch,
                    onValueChange = { checked = !checked }
                )
                .semantics(mergeDescendants = true) {
                    // Set any explicit semantic properties
                    stateDescription = if (checked) stateSubscribed else stateNotSubscribed
                }
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Here you can subscribe",
                Modifier
                    .weight(1f)
                    .clearAndSetSemantics { })
            Checkbox(checked = checked, onCheckedChange = null)
        }
        StateDescriptionSlider()
        Greeting("Android", onExit = {})
    }
}

@SuppressLint("RememberReturnType")
@Preview
@Composable
fun StateDescriptionSlider(){
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
           modifier = Modifier.semantics(mergeDescendants = true) {
            // Set any explicit semantic properties
            stateDescription =  "${sliderPosition*100} percent"
        }
        )
        Text(text = sliderPosition.toString())
    }

}
@Composable
private fun TopicItem(itemTitle: String, selected: Boolean, onToggle: () -> Unit) {
    val stateSubscribed = stringResource(R.string.subscribed)
    val stateNotSubscribed = stringResource(R.string.unsubscribed)
    Row(
        modifier = Modifier
            .semantics {
                // Set any explicit semantic properties
                stateDescription = if (selected) stateSubscribed else stateNotSubscribed
            }
            .toggleable(
                value = selected,
                onValueChange = { onToggle() }
            )
    ) {
        /* ... */
    }
}
fun onToggle() {
    Log.d("test", "toggled")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AccessibilityMobileMasterClassTheme {
        Greeting("Android", onExit = {})
    }
}
