package com.example.mediaexplorer.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mediaexplorer.Home
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import com.example.mediaexplorer.R
import com.example.mediaexplorer.TypeContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateContentScreen(
    navController: NavHostController,
    categoryId: Int,
) {
    var expanded by remember { mutableStateOf(false) }
    var typeSelec by remember { mutableStateOf<TypeContent?>(null) }

    //genericos
    var name by remember { mutableStateOf("") }
    var information by remember { mutableStateOf("") }

    //especificos
    var duration by remember { mutableStateOf("") }
    var cantCap by remember { mutableStateOf("") }
    var typeGenre by remember { mutableStateOf("") }

    //validaciones
    var errorName by remember { mutableStateOf("") }
    var errorInfo by remember { mutableStateOf("") }
    var errorExtra by remember { mutableStateOf("") }
    var errorExtra2 by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Button(
                            onClick = {
                                val popped = navController.popBackStack()
                                if (!popped) navController.navigate(Home)
                            },
                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                        Text(
                            text = stringResource(R.string.add_content),
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                        )

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            val isValid = validateForm(
                                typeSelec = typeSelec,
                                name = name,
                                information = information,
                                duration = duration,
                                cantCap = cantCap,
                                typeGenre = typeGenre,
                                errorName = { errorName = it },
                                errorInfo = { errorInfo = it },
                                errorExtra = { errorExtra = it },
                                errorExtra2 = { errorExtra2 = it }
                            )
//                            when (typeSelec?.type) {
//                                TypeContent.PELICULA -> {
//                                    if (isValid){
//                                        val movie = Movies(
//                                            id = ListMovies.size + 1,
//                                            name = name,
//                                            information = information,
//                                            category = TypeContent.PELICULA,
//                                            imageResId = R.drawable.placeholder,
//                                            duration = duration.toIntOrNull() ?: 0,
//                                        )
//                                        ListMovies.add(movie)
//                                        navController.popBackStack()
//                                    }
//                                }
//                                TypeContent.SERIE -> {
//                                    if (isValid){
//                                        val serie = Series(
//                                            id = ListSeries.size + 1,
//                                            name = name,
//                                            information = information,
//                                            category = TypeContent.SERIE,
//                                            imageResId = R.drawable.placeholder,
//                                            cantCap = cantCap.toIntOrNull() ?: 0,
//                                        )
//                                        ListSeries.add(serie)
//                                        navController.popBackStack()
//                                    }
//                                }
//                                TypeContent.ANIME -> {
//                                    if (isValid){
//                                        val anime = Anime(
//                                            id = ListAnimes.size + 1,
//                                            name = name,
//                                            information = information,
//                                            category = TypeContent.ANIME,
//                                            imageResId = R.drawable.placeholder,
//                                            cantCap = cantCap.toIntOrNull() ?: 0,
//                                            typeGenre = typeGenre,
//                                        )
//                                        ListAnimes.add(anime)
//                                        navController.popBackStack()
//                                    }
//                                }
//                                TypeContent.OTRO -> {
//                                    if (isValid){
//                                        val custom = OtherContent(
//                                            id = ListCustomCont.size + 1,
//                                            name = name,
//                                            information = information,
//                                            category = TypeContent.OTRO,
//                                            imageResId = R.drawable.placeholder,
//                                            typeContent = typeSelec?.name ?: "Otro",
//                                        )
//                                        ListCustomCont.add(custom)
//                                        navController.popBackStack()
//                                    }
//                                }
//                                else -> {  }
//                            }
                        },
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp),
                    ) {
                        Text(text = stringResource(R.string.save_btn))
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.content_type),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box {
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD84040),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text(
                        typeSelec?.name ?: stringResource(R.string.select_category)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(x = 120.dp, y = 0.dp) // probar valores según el ancho
                ) {
                    TypeContent.values().forEach { tipo ->
                        DropdownMenuItem(
                            text = {
                                Text(tipo.name)
                            },
                            onClick = {
                                typeSelec = tipo
                                expanded = false
                            }
                        )
                    }
                }
            }

            if (typeSelec!=null) {
                when (typeSelec) {
                    TypeContent.PELICULA ->{
                        OutlinedTextField(
                            value = name,
                            onValueChange = {name = it},
                            label = { Text(text = stringResource(R.string.category_name) + " ${typeSelec?.name?.lowercase() ?: "del contenido"}")},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorName.isNotEmpty()) {
                            Text(text = errorName, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = information,
                            onValueChange = {information = it},
                            label = { Text(text = stringResource(R.string.content_info))},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorInfo.isNotEmpty()) {
                            Text(text = errorInfo, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = duration,
                            onValueChange = {duration = it},
                            label = { Text("Duración (min)")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorExtra.isNotEmpty()) {
                            Text(text = errorExtra, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                    TypeContent.SERIE -> {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {name = it},
                            label = { Text(text = stringResource(R.string.category_name) + " ${typeSelec?.name?.lowercase() ?: "del contenido"}")},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorName.isNotEmpty()) {
                            Text(text = errorName, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = information,
                            onValueChange = {information = it},
                            label = { Text(text = stringResource(R.string.content_info))},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorInfo.isNotEmpty()) {
                            Text(text = errorInfo, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = cantCap,
                            onValueChange = {cantCap = it},
                            label = { Text("Cantidad de Capítulos")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorExtra.isNotEmpty()) {
                            Text(text = errorExtra, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                    TypeContent.ANIME -> {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {name = it},
                            label = { Text(text = stringResource(R.string.category_name) + " ${typeSelec?.name?.lowercase() ?: "del contenido"}")},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorName.isNotEmpty()) {
                            Text(text = errorName, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = information,
                            onValueChange = {information = it},
                            label = { Text(text = stringResource(R.string.content_info))},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorInfo.isNotEmpty()) {
                            Text(text = errorInfo, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = cantCap,
                            onValueChange = {cantCap = it},
                            label = { Text("Cantidad de Capítulos")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorExtra.isNotEmpty()) {
                            Text(text = errorExtra, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = typeGenre,
                            onValueChange = {typeGenre = it},
                            label = { Text("Género Anime")},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorExtra2.isNotEmpty()) {
                            Text(text = errorExtra2, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                    else -> {
                        OutlinedTextField(
                            value = name,
                            onValueChange = {name = it},
                            label = { Text(text = stringResource(R.string.category_name) + " ${typeSelec?.name?.lowercase() ?: "del contenido"}")},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorName.isNotEmpty()) {
                            Text(text = errorName, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                        OutlinedTextField(
                            value = information,
                            onValueChange = {information = it},
                            label = { Text(text = stringResource(R.string.content_info))},
                            modifier = Modifier.padding(10.dp),
                            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary)
                        )
                        if (errorInfo.isNotEmpty()) {
                            Text(text = errorInfo, color = Color.Red, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                }
            }
        }
    }
}

fun validateForm(
    typeSelec: TypeContent?,
    name: String,
    information: String,
    duration: String = "",
    cantCap: String = "",
    typeGenre: String = "",
    errorName: (String) -> Unit,
    errorInfo: (String) -> Unit,
    errorExtra: (String) -> Unit,
    errorExtra2: (String) -> Unit,
): Boolean {
    var isValid = true

    errorName("")
    errorInfo("")
    errorExtra("")
    errorExtra2("")

    if (name.isBlank()) {
        errorName("El nombre es obligatorio")
        isValid = false
    }

    if (information.isBlank()) {
        errorInfo("La sinopsis/descripcion es obligatoria")
        isValid = false
    }

    when (typeSelec) {
        TypeContent.PELICULA -> {
            if (duration.isBlank()) {
                errorExtra("La duración es obligatoria")
                isValid = false
            } else if (duration.toIntOrNull() == null || duration.toInt() <= 0) {
                errorExtra("La duracion debe ser mayor a 0")
                isValid = false
            }
        }
        TypeContent.SERIE, TypeContent.ANIME -> {
            if (cantCap.isBlank()) {
                errorExtra("Cantidad de capítulos obligatoria")
                isValid = false
            } else if (cantCap.toIntOrNull() == null || cantCap.toInt() <= 0) {
                errorExtra("Debe ser un número mayor a 0")
                isValid = false
            }

            if (typeSelec == TypeContent.ANIME && typeGenre.isBlank()) {
                errorExtra2("El género del anime es obligatorio")
                isValid = false
            }
        }
        else -> {  }
    }
    return isValid
}