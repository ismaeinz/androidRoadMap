package com.example.grid.responsive


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.grid.R

@Composable
fun ProfileScreenResponsive() {
    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.Compact -> CompactScreen()
        WindowType.Medium -> MediumScreen()
        else -> ExpandedScreen()
    }
}

@Composable
fun CompactScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start

    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
        PersonalData("Name", "MkrDeveloper")
        PersonalData("Email", "john.mckinley@examplepetstore.com")
        PersonalData("Phone", "0123456789")
    }
}

@Composable
fun MediumScreen() {

    Row(
        modifier = Modifier
            .fillMaxSize()

            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(400.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            PersonalData("Name", "MkrDeveloper")
            PersonalData("Email", "john.mckinley@examplepetstore.com")
            PersonalData("Phone", "0123456789")
        }
    }
}

@Composable
fun ExpandedScreen() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(400.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            PersonalData("Name", "MkrDeveloper")
            PersonalData("Email", "john.mckinley@examplepetstore.com")
            PersonalData("Phone", "0123456789")
        }
    }
}

@Composable
fun PersonalData(title: String, content: String) {
    Column(Modifier.padding(12.dp)) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = content)
        Spacer(modifier = Modifier.height(6.dp))
    }
}

