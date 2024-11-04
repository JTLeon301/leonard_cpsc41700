package com.example.leonard.circuitsphere

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leonard.circuitsphere.model.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductListAdapter
    private lateinit var floatingActionButton: FloatingActionButton

    private var productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        //Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Initialize FloatingActionButton
        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Initialize Product List
        initializeProductList()

        //Set up the initial adapter with all products
        setAdapter(productList)
    }

    private fun initializeProductList() {
        //Add all products to the productList
        productList.addAll(listOf(
            //Graphics Cards
            Product(name = "Geforce GTX 1070ti",
                partNumber = "GTX1070TI-A8G-GAMING",
                description = "ASUS ROG GeForce GTX 1070 Ti ROG-STRIX-GTX1070TI-A8G-GAMING 8GB 256-Bit GDDR5 PCI Express 3.0 HDCP Ready SLI Support Video Card",
                price = 239.99,
                imageId = R.drawable.gtx1070ti,
                category = "Graphics Cards"),
            Product(name = "Geforce RTX 2080 Super",
                partNumber = "ROG-STRIX-RTX2080S-ASNRFB",
                description = "ASUS ROG-STRIX-RTX2080S-A8G-GAMING ROG STRIX GeForce RTX 2080 SUPER Video Card",
                price = 599.00,
                imageId = R.drawable.rtx2080s,
                category = "Graphics Cards"),
            Product(name = "Geforce RTX 4070",
                partNumber = "GV-N4070GAMING OCV2-12GD",
                description = "GIGABYTE GeForce RTX 4070 GAMING OC V2 12G Graphics Card, 3x WINDFORCE Fans, 12GB 192-bit GDDR6X, GV-N4070GAMING OCV2-12GD Video Card",
                price = 559.99,
                imageId = R.drawable.rtx4070,
                category = "Graphics Cards"),
            //Processors
            Product(name = "Intel Core i7-8700k",
                partNumber = "BX80684I78700K",
                description = "Intel Core i7 8th Gen - Core i7-8700K Coffee Lake 6-Core 3.7 GHz (4.7 GHz Turbo) LGA 1151 (300 Series) 95W BX80684I78700K Desktop Processor Intel UHD Graphics 630",
                price = 181.65,
                imageId = R.drawable.corei7_8700k,
                category = "Processors"),
            Product(name = "Intel Core i5-12400",
                partNumber = "BX8071512400",
                description = "Intel Core i5-12400 - Core i5 12th Gen Alder Lake 6-Core 2.5 GHz LGA 1700 65W Intel UHD Graphics 730 Desktop Processor - BX8071512400",
                price = 142.99,
                imageId = R.drawable.corei5_12400,
                category = "Processors"),
            Product(name = "Ryzen 9 7900X3D",
                partNumber = "100-100000909WOF",
                description = "AMD Ryzen 9 7900X3D - Ryzen 9 7000 Series 12-Core 4.4 GHz Socket AM5 120W AMD Radeon Graphics Desktop Processor - 100-100000909WOF",
                price = 373.99,
                imageId = R.drawable.ryzen9_7900x3d,
                category = "Processors"),
            //RAM Sticks
            Product(name = "TRIDENT Z5 RGB 32gb",
                partNumber = "F5-6000J3636F16GX2-TZ5RK",
                description = "G.SKILL Trident Z5 RGB Series 32GB (2 x 16GB) 288-Pin PC RAM DDR5 6000 (PC5 48000) Desktop Memory Model F5-6000J3636F16GX2-TZ5RK",
                price = 112.99,
                imageId = R.drawable.trident_z5_32gb,
                category = "RAM Sticks"),
            Product(name = "G.SKILL Ripjaws S5 64gb",
                partNumber = "F5-6000J3040G32GX2-RS5K",
                description = "G.SKILL Ripjaws S5 Series 64GB (2 x 32GB) 288-Pin PC RAM DDR5 6000 (PC5 48000) Desktop Memory Model F5-6000J3040G32GX2-RS5K",
                price = 197.99,
                imageId = R.drawable.gskill_ripjaws_64gb,
                category = "RAM Sticks"),
            Product(name = "CORSAIR Vengeance RGB 32gb",
                partNumber = "CMH32GX5M2B6000C40L",
                description = "CORSAIR Vengeance RGB 32GB (2 x 16GB) 288-Pin PC RAM DDR5 6000 (PC5 48000) Desktop Memory Model CMH32GX5M2B6000C40L",
                price = 109.99,
                imageId = R.drawable.corsair_vengeance_32gb,
                category = "RAM Sticks"),
            //Power Supplies
            Product(name = "CORSAIR RM1000e ATX",
                partNumber = "CP-9020264-NA",
                description = "CORSAIR RM1000e Fully Modular Low-Noise ATX Power Supply - ATX 3.0 & PCIe 5.0 Compliant - 105°C-Rated Capacitors - 80 PLUS Gold Efficiency - Modern Standby Support",
                price = 159.99,
                imageId = R.drawable.corsair_rm1000e,
                category = "Power Supplies"),
            Product(name = "Thermaltake Smart Series 700W",
                partNumber = "PS-SPD-0700NPCWUS-W",
                description = "Thermaltake Smart Series 700W SLI / CrossFire Ready Continuous Power ATX12V V2.3 / EPS12V 80 PLUS Certified Active PFC Power Supply Haswell Ready PS-SPD-0700NPCWUS-W",
                price = 54.99,
                imageId = R.drawable.thermaltake_700w,
                category = "Power Supplies"),
            Product(name = "EVGA SuperNOVA 1300 ATX",
                partNumber = "220-PP-1300-X1",
                description = "EVGA SuperNOVA 1300 P+ 220-PP-1300-X1 1300 W ATX12V / EPS12V 80 PLUS PLATINUM Certified Full Modular Active PFC Power Supply\n",
                price = 274.99,
                imageId = R.drawable.evga_1300w,
                category = "Power Supplies"),
            //Cases
            Product(name = "Fractal Design North ATX",
                partNumber = "FD-C-NOR1C-02",
                description = "Fractal Design North ATX mATX Mid Tower PC Case - North Charcoal Black with Walnut Front and Dark Tinted TG Side Panel",
                price = 139.97,
                imageId = R.drawable.fractal_north,
                category = "Cases"),
            Product(name = "LIAN LI O11 Dynamic EVO RGB",
                partNumber = "O11DERGBX",
                description = "LIAN LI O11 Dynamic EVO RGB Black Aluminum / Steel / Tempered Glass ATX Mid Tower Computer Case----O11DERGBX",
                price = 159.96,
                imageId = R.drawable.o11_dynamic,
                category = "Cases"),
            Product(name = "CORSAIR 6500X Mid-Tower Dual Chamber",
                partNumber = "CC-9011258-WW",
                description = "CORSAIR 6500X Mid-Tower Dual Chamber PC Case – White - Unobstructed view with wraparound front and side glass panels – Fits up to 10x 120mm fans – 4x Radiator Mounting Positions",
                price = 169.99,
                imageId = R.drawable.corsair_6500x,
                category = "Cases"),
            //Phones
            Product(name = "SAMSUNG Galaxy S24 Ultra",
                partNumber = "SM-S921B",
                description = "SAMSUNG Galaxy S24 Ultra Cell Phone, 512GB AI Smartphone, Unlocked Android, 50MP Zoom Camera, Long Battery Life, S Pen, US Version, 2024, Titanium Yellow",
                price = 1413.99,
                imageId = R.drawable.samsung_s24,
                category = "Phones"),
            Product(name = "Google Pixel 8 Pro",
                partNumber = "G8ZUC",
                description = "Google Pixel 8 Pro 5G Dual 128GB 12GB RAM Universal Unlocked Smartphone with Advanced Pixel Camera, 24-Hour Battery Obsidian",
                price = 908.99,
                imageId = R.drawable.pixel8_pro,
                category = "Phones"),
            Product(name = "Apple iPhone 12 Pro",
                partNumber = "A2407",
                description = "Apple iPhone 12 Pro Verizon Locked 6GB/256GB - Silver",
                price = 444.00,
                imageId = R.drawable.iphone12_pro,
                category = "Phones"),
        ))
    }

    private fun setAdapter(products: List<Product>) {
        productAdapter = ProductListAdapter(products) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("product", product)
            }
            startActivity(intent)
        }
        recyclerView.adapter = productAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //Menu to filter the category of items the user wants to look at
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_graphics_cards -> {
                filterProductsByCategory("Graphics Cards") //Show graphics cards
                true
            }
            R.id.menu_processors -> {
                filterProductsByCategory("Processors") //Show processors
                true
            }
            R.id.menu_ram -> {
                filterProductsByCategory("RAM Sticks") //Show ram sticks
                true
            }
            R.id.menu_power_supplies -> {
                filterProductsByCategory("Power Supplies") //Show power supplies
                true
            }
            R.id.menu_cases -> {
                filterProductsByCategory("Cases") //Show cases
                true
            }
            R.id.menu_phones -> {
                filterProductsByCategory("Phones") //Show phones
                true
            }
            R.id.menu_all -> {
                setAdapter(productList) //Show all products
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filterProductsByCategory(category: String) {
        val filteredList = productList.filter { it.category == category }
        productAdapter.updateList(filteredList)
    }
}


