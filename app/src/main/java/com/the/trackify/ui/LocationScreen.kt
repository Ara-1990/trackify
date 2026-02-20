package com.the.trackify.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.the.trackify.service.LocationService

@Composable
fun LocationScreen(viewModel: MainViewModel, onStartTracking: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = onStartTracking)

         {
            Text("Start Tracking")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.stopTracking()
        }) {
            Text("Stop Tracking")
        }
    }

    }

