package com.example.uaspam.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHalamanHome : DestinasiNavigasi {
    override val route = "mainScreen"
    override val titleRes = "Main Screen"
}

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Buku"
}

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail"
    override val titleRes = "Detail Buku"
    const val IDBUKU = "idBuku"
    val routeWithArgs = "$route/{$IDBUKU}"
}

object DestinasiUpdate: DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Buku"
}

object DestinasiKategori : DestinasiNavigasi {
    override val route = "kategori"
    override val titleRes = "Home Kategori"
}

object DestinasiDKategori : DestinasiNavigasi {
    override val route = "detail Kategori"
    override val titleRes = "Detail Kategori"
    const val IDKATEGORI = "idKategori"
    val routeWithArgs = "$route/{$IDKATEGORI}"
}

object DestinasiUpKategori : DestinasiNavigasi {
    override val route = "update"
    override val titleRes = "Update Kategori"
}

object DestinasiPenulis : DestinasiNavigasi {
    override val route = "penulis"
    override val titleRes = "Home Penulis"
}





