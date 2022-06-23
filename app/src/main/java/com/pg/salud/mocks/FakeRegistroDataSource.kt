package com.pg.salud.mocks

import com.pg.salud.models.registro.task.Registro

class FakeRegistroDataSource {
    companion object {
        fun createDataSet(): MutableList<Registro> {
            val list = ArrayList<Registro>()

            list.add(
                Registro(
                    "Miguel",
                    "Las pantallas NFT físicas se convierten en la nueva moda para los coleccionistas",
                    "miguel@gmail.com",
                    "SUs"
                )
            )

            list.add(
                Registro(
                    "Enrique",
                    "Obra de Andy Warhol se venderá en 961 fragmentos NFT",
                    "enrique@gmail.com",
                    "SUs"
                )
            )

            list.add(
                Registro(
                    "André",
                    "La plataforma DeFi Marhaba aprovecha el sector financiero islámico de USD 3 billones con NFT compatibles con Halal",
                    "andre@gmail.com",
                    "SUs"
                )
            )
            return list
        }
    }
}