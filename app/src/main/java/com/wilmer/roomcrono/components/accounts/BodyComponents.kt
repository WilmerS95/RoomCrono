package com.wilmer.roomcrono.components.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wilmer.roomcrono.model.accounts.AccountsModel
import com.wilmer.roomcrono.ui.theme.darkCerulean
import java.text.DecimalFormat
import java.util.Locale

@Composable
fun CardAccount(accountsModel: AccountsModel, onClick: (Int) -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .shadow(30.dp)
            .clickable { onClick(accountsModel.id) }
            .fillMaxWidth()
            .background(color = Color.Gray)
    ) {
        Row (
            modifier = Modifier
                .background(color = Color.White)
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ){
                Text(
                    text = accountsModel.tipoCuenta.nombre.uppercase(Locale.ROOT),
                    fontSize = 16.sp,
                    color = darkCerulean,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = accountsModel.cuenta.toString(),
                    color = darkCerulean,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = accountsModel.nombre.uppercase(Locale.ROOT),
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = accountsModel.usuario.nombre.toString(),
                    color = darkCerulean,
                    fontWeight = FontWeight.Bold)
                
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ){
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favorites",
                    modifier = Modifier.size(40.dp)
                        //.padding(end = 10.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.End)
                {
                    Text(
                        text = "L",
                        fontSize = 20.sp,
                        color = darkCerulean,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(14.dp))
                    Text(
                        text = accountsModel.saldo.formatToCurrency(),
                        fontSize = 20.sp,
                        color = darkCerulean,
                        fontWeight = FontWeight.Bold)
                }
                Text(text = "Saldo disponible", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
        Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.Gray))
        Spacer(modifier = Modifier.height(40.dp).fillMaxWidth().background(color = Color.White))
    }
}

fun Double.formatToCurrency(): String {
    val formatter = DecimalFormat("#,##0.00")
    return formatter.format(this)
}