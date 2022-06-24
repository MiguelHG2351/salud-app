package com.pg.salud.mocks

import com.pg.salud.models.registro.task.Registro
import com.pg.salud.models.registro.task.Time

class FakeRegistroDataSource {
    companion object {
        fun createDataSet(): MutableList<Registro> {
            val list = ArrayList<Registro>()

            list.add(
                Registro(
                    25.00,
                    4.5,
                    60.4,
                    Time(1655997618, 31000000)
                )
            )

            list.add(
                Registro(
                    25.00,
                    4.5,
                    60.4,
                    Time(1655997618, 31000000)
                )
            )

            list.add(
                Registro(
                    25.00,
                    4.5,
                    60.4,
                    Time(1655997618, 31000000)
                )
            )
            return list
        }
    }
}