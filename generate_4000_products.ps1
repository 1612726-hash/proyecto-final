# Generator for 4000 Retro & Gaming Products
$categories = @(
    @{ Id = 2; Code = "SNES"; Name = "Super Nintendo"; MinPrice = 18.0; MaxPrice = 95.0 },
    @{ Id = 3; Code = "GEN"; Name = "Sega Genesis"; MinPrice = 15.0; MaxPrice = 85.0 },
    @{ Id = 4; Code = "ARC"; Name = "Arcade"; MinPrice = 25.0; MaxPrice = 180.0 },
    @{ Id = 5; Code = "N64"; Name = "Nintendo 64"; MinPrice = 20.0; MaxPrice = 110.0 },
    @{ Id = 6; Code = "GBA"; Name = "Game Boy / GBA"; MinPrice = 15.0; MaxPrice = 90.0 },
    @{ Id = 7; Code = "GCW"; Name = "Nintendo GameCube / Wii"; MinPrice = 18.0; MaxPrice = 80.0 },
    @{ Id = 8; Code = "NSW"; Name = "Nintendo Switch"; MinPrice = 25.0; MaxPrice = 70.0 },
    @{ Id = 9; Code = "PS1"; Name = "PlayStation 1"; MinPrice = 15.0; MaxPrice = 85.0 },
    @{ Id = 10; Code = "PS2"; Name = "PlayStation 2"; MinPrice = 12.0; MaxPrice = 75.0 },
    @{ Id = 11; Code = "PS5"; Name = "PlayStation 4 / 5"; MinPrice = 20.0; MaxPrice = 70.0 },
    @{ Id = 12; Code = "XBX"; Name = "Xbox Clasica & 360"; MinPrice = 12.0; MaxPrice = 65.0 },
    @{ Id = 13; Code = "CTR"; Name = "Mandos y Controles"; MinPrice = 18.0; MaxPrice = 95.0 },
    @{ Id = 14; Code = "HW"; Name = "Consolas y Hardware"; MinPrice = 75.0; MaxPrice = 420.0 },
    @{ Id = 15; Code = "CAB"; Name = "Accesorios y Cables"; MinPrice = 5.0; MaxPrice = 35.0 },
    @{ Id = 16; Code = "FIG"; Name = "Figuras y Coleccionables"; MinPrice = 12.0; MaxPrice = 115.0 },
    @{ Id = 17; Code = "MRC"; Name = "Ropa y Merch Gamer"; MinPrice = 10.0; MaxPrice = 45.0 },
    @{ Id = 18; Code = "MEM"; Name = "Memorias y Almacenamiento"; MinPrice = 8.0; MaxPrice = 55.0 }
)

# Base vocabularies for realistic title generation per category
$gameTitles = @(
    "Super Mario", "Chrono", "Final Fantasy", "Castlevania", "Metroid", "Mega Man", "EarthBound", "Secret of Mana",
    "Donkey Kong", "Street Fighter", "Contra", "Super Castlevania", "TMNT Turtles in Time", "F-Zero", "Star Fox",
    "Breath of Fire", "Super Ghouls 'n Ghosts", "Tales of Phantasia", "Lufia", "Super Bomberman", "Kirby Super Star",
    "Harvest Moon", "Terranigma", "Illusion of Gaia", "Sunset Riders", "Wild Guns", "Zombies Ate My Neighbors",
    "Aladdin", "Lion King", "Super Mario Kart", "Super Mario All-Stars", "Demon's Crest", "Shadowrun", "R-Type",
    "Gradius", "Axelay", "Super Punch-Out", "ActRaiser", "Ogre Battle", "SimCity Retro", "Super Adventure Island",
    "Sonic", "Streets of Rage", "Golden Axe", "Phantasy Star", "Shinobi", "Gunstar Heroes", "Comix Zone",
    "VectorMan", "Altered Beast", "Ristar", "Wonder Boy", "Toejam & Earl", "Shining Force", "Thunder Force",
    "OutRun", "After Burner", "Road Rash", "Earthworm Jim", "Ecco the Dolphin", "Mortal Kombat", "Virtua Racing",
    "Landstalker", "Rocket Knight", "Kid Chameleon", "Crusader of Centy", "Alien Soldier", "Splatterhouse",
    "Metal Slug", "King of Fighters", "Fatal Fury", "Samurai Shodown", "Street Fighter II Champion", "Tekken",
    "Darkstalkers", "Marvel vs Capcom", "Cadillacs and Dinosaurs", "Captain Commando", "The Punisher", "Snow Bros",
    "Bubble Bobble", "Windjammers", "Puzzle Bobble", "Raiden", "Strikers 1945", "1942 Arcade", "DonPachi",
    "Super Mario 64", "The Legend of Zelda Ocarina", "Majora's Mask", "GoldenEye 007", "Banjo-Kazooie",
    "Banjo-Tooie", "Super Smash Bros", "Mario Kart 64", "Perfect Dark", "Paper Mario", "Conker's Bad Fur Day",
    "Diddy Kong Racing", "Star Wars Rogue Squadron", "Wave Race 64", "F-Zero X", "Rayman 2", "Turok Dinosaur Hunter",
    "Pokemon Rojo Fuego", "Pokemon Esmeralda", "Pokemon Cristal", "Pokemon Rubi", "Pokemon Zafiro", "Golden Sun",
    "Advance Wars", "Fire Emblem", "Metroid Fusion", "Metroid Zero Mission", "Castlevania Aria of Sorrow",
    "The Legend of Zelda Minish Cap", "Mario & Luigi Superstar Saga", "WarioWare Inc", "Mega Man Battle Network",
    "Super Smash Bros Melee", "The Wind Waker", "Twilight Princess", "Super Mario Sunshine", "Luigi's Mansion",
    "Metroid Prime", "Pikmin", "Super Paper Mario", "Mario Galaxy", "Resident Evil 4", "F-Zero GX", "Super Mario Odyssey",
    "The Legend of Zelda Tears", "Breath of the Wild", "Mario Kart 8 Deluxe", "Metroid Dread", "Xenoblade Chronicles",
    "Resident Evil", "Silent Hill", "Crash Bandicoot", "Spyro the Dragon", "Metal Gear Solid", "Gran Turismo",
    "Tony Hawk's Pro Skater", "Final Fantasy VII", "Final Fantasy VIII", "Final Fantasy IX", "Castlevania Symphony",
    "Grand Theft Auto San Andreas", "Grand Theft Auto Vice City", "Shadow of the Colossus", "God of War",
    "Kingdom Hearts", "Persona 3", "Persona 4", "Devil May Cry", "Halo Combat Evolved", "Halo 2", "Gears of War",
    "Mass Effect", "BioShock", "Red Dead Redemption", "Elder Scrolls Skyrim", "Fallout New Vegas", "Dark Souls"
)

$editions = @(
    "Original CIB", "Edicion Coleccionista", "Version Japonesa NTSC-J", "Edicion Clasica", "Remastered HD",
    "Copia Sellada", "Cartucho Suelto", "Caja con Manual", "Player's Choice", "Greatest Hits", "Edicion Limitada",
    "Black Label", "Edicion Aniversario", "Version GOTY", "Steelbook Edition", "Edicion Deluxe", "Edicion Retro Especial"
)

$hardwareItems = @(
    "Consola Super Nintendo SNES Jr", "Consola SNES Modelo 1 Original", "Consola Sega Genesis Modelo 2",
    "Consola Sega Genesis Modelo 1 HD Graphics", "Consola Sega Dreamcast Blanca", "Consola Sega Saturn Japonesa",
    "Consola Nintendo 64 Charcoal Grey", "Consola Nintendo 64 Funtastic Jungle Green", "Consola Nintendo 64 Smoke Black",
    "Consola Nintendo 64 Gold Edition", "Consola GameCube Indigo con Game Boy Player", "Consola GameCube Platinum Silver",
    "Consola Game Boy DMG-01 Original Gris", "Consola Game Boy Pocket Silver", "Consola Game Boy Color Atomic Purple",
    "Consola Game Boy Advance Indigo", "Consola Game Boy Advance SP AGS-101 Pantalla Brillante", "Consola Nintendo DS Lite",
    "Consola Nintendo 3DS XL", "Consola Nintendo Switch OLED Neon", "Consola Nintendo Switch Lite Turquesa",
    "Consola PlayStation 1 SCPH-1001 Audiophile", "Consola PS One Slim con Pantalla LCD", "Consola PlayStation 2 Fat Negra",
    "Consola PlayStation 2 Slim Plata", "Consola PlayStation 3 Slim 320GB", "Consola PlayStation 4 Pro 1TB",
    "Consola PlayStation 5 Edicion Disco", "Consola Xbox Original con Disco 500GB", "Consola Xbox 360 Slim Negra",
    "Monitor CRT Sony Trinitron 14 Pulgadas PVM", "Monitor CRT Trinitron 20 Pulgadas Retro",
    "Bartop Arcade 2 Jugadores Pandora Box", "Maquina Arcade Mini Neogeo Bartop", "Consola Portatil Anbernic Retro"
)

$controllerItems = @(
    "Mando Inalambrico 8BitDo SN30 Pro", "Mando Original Super Nintendo OEM", "Mando Sega Genesis 6 Botones OEM",
    "Mando Inalambrico 8BitDo M30 Genesis", "Mando Nintendo 64 Original Stick Firme", "Mando Brawler64 Retro Fighters",
    "Mando Tribute64 N64 Inalambrico", "Mando Nintendo GameCube WaveBird Inalambrico", "Mando GameCube Smash Bros Negro",
    "Mando DualShock 1 Original Gris", "Mando DualShock 2 Negro Original Sony", "Mando DualShock 4 Wireless Negro",
    "Mando DualSense PS5 Midnight Black", "Mando Xbox Controller S Original", "Mando Xbox 360 Inalambrico Negro",
    "Arcade Stick Mayflash F500 Universal", "Arcade Stick Hori Real Arcade Pro 4", "Palanca Sanwa JLF con Restrictor Octagonal",
    "Kit 6 Botones Sanwa OBSF-30 Arcade", "Volante con Pedales Force Feedback Retro", "Pistola Light Gun Konami Justifier"
)

$accessoryItems = @(
    "Cable RGB SCART blindado con CSYNC", "Cable Componente HD 720p/1080i", "Adaptador RetroTINK-2X Pro Escalador HDMI",
    "Adaptador RetroTINK-5X Pro 4K Escalador", "Cable AV RCA Premium Doble Blindaje", "Fuente de Poder Regulada Triad 9V",
    "Fuente de Poder Original OEM", "Cable de Extension para Mando 1.8m", "Adaptador Bitfunx HDMI con Audio Optico",
    "Kit de Reemplazo de Condensadores Recapping", "Pila CR2032 con Terminales para Cartucho", "Destornillador Gamebit 3.8mm y 4.5mm",
    "Kit Limpieza de Conectores y Pines 1UPCard", "Funda Protectora de Acrilico para Caja", "Protector PET 0.5mm para Cartucho",
    "Cable Link Universal 4 Jugadores", "Lupa con Luz Worm Light", "Lente Laser de Reemplazo KSM-440", "Kit de Modificacion HDMI Interno"
)

$collectibleItems = @(
    "Figura Amiibo Mario 30 Aniversario", "Figura Amiibo Link Ocarina of Time", "Figura Amiibo Samus Metroid Dread",
    "Estatua First 4 Figures Mega Man 30cm", "Figura Nendoroid Red Pokemon Trainer", "Figura Funko Pop Sonic the Hedgehog Retro",
    "Figura Funko Pop Crash Bandicoot Chase", "Set de Pines Esmaltados Medallas Kanto", "Diorama Cubo Acrilico 3D Super Mario World",
    "Diorama Cubo Acrilico 3D Zelda A Link to the Past", "Diorama Cubo Acrilico 3D Castlevania SOTN", "Lampara LED Pixel Pal Mega Man",
    "Lampara Neon Pac-Man Oficial Namco", "Cartel Metalico Vintage Donkey Kong Arcade", "Moneda Conmemorativa Coleccionista Nintendo",
    "Caja Musical Zelda Song of Storms de Madera", "Artbook Oficial The Legend of Zelda Encyclopedia", "Guia Prima Games Retro Coleccionista"
)

$merchItems = @(
    "Camiseta Algodon Super Mario 1985 8-Bit", "Camiseta Sega Genesis 16-Bit Power", "Camiseta PlayStation Classic Logo Retro",
    "Camiseta Metroid Samus Aran Dark Navy", "Gorra Snapback Nintendo Original con Bordado 3D", "Gorra Sega Megadrive Edicion Limitada",
    "Sudadera con Capucha Zelda Triforce Hylian", "Sudadera Gamer Pocket Neo Geo Arcade", "Taza Termica Cambia Color Game Boy",
    "Taza Ceramica Taza Cafe Pac-Man Ghost", "Mousepad XXL Escritorio Mapa Super Mario World", "Mousepad XXL Cyberpunk Retro Arcade",
    "Llavero Metalico Mando Super Nintendo", "Llavero Metalico Espada Maestra Zelda", "Billetera Clasica Mando NES Nintendo",
    "Mochila Antirrobo Estilo Consola Retro", "Calcetines Gamers Pack 3 Pares Retro Pixel", "Manta Polar Estampado Pokemon 1ra Gen"
)

$memoryItems = @(
    "Cartucho EverDrive-64 X7 Krikzz Original", "Cartucho FXPAK Pro SD2SNES Krikzz", "Cartucho Mega EverDrive Pro Sega CD Support",
    "Cartucho EZ-Flash Omega Definitive Edition GBA", "Memory Card PlayStation 1 Original Sony 1MB", "Memory Card PlayStation 2 Sony 8MB MagicGate",
    "Memory Card PS2 FMCB Free McBoot 64MB", "Memory Card GameCube Original 1019 Bloques", "Tarjeta MicroSD Sandisk Ultra 128GB para Switch",
    "Tarjeta MicroSD Samsung Evo Plus 256GB Switch", "Tarjeta MicroSD Kingston Canvas 512GB", "Disco Duro Externo 2TB USB 3.0 para PS4/PS5",
    "Adaptador MicroSD a Memory Card PS Vita SD2Vita", "Controller Pak Nintendo 64 Original", "Tremor Pak Rumble Pak Nintendo 64"
)

Write-Host "Iniciando generacion de 4000 productos..."

$products = [System.Collections.Generic.List[PSObject]]::new()
$usedCodes = [System.Collections.Generic.HashSet[string]]::new()
$usedNames = [System.Collections.Generic.HashSet[string]]::new()

$totalNeeded = 4000
$codeIndex = 1

# Target counts per category to reach exactly 4000
$targetPerCategory = [math]::Floor($totalNeeded / $categories.Count)
$remainder = $totalNeeded % $categories.Count

$catIndex = 0
foreach ($cat in $categories) {
    $countForCat = $targetPerCategory
    if ($catIndex -lt $remainder) {
        $countForCat += 1
    }
    $catIndex++
    
    Write-Host "Generando $countForCat productos para $($cat.Name)..."
    $generatedForThisCat = 0
    $subIndex = 1
    
    while ($generatedForThisCat -lt $countForCat) {
        $price = [math]::Round((Get-Random -Minimum ($cat.MinPrice * 100) -Maximum ($cat.MaxPrice * 100)) / 100.0, 2)
        $stock = Get-Random -Minimum 3 -Maximum 65
        
        $name = ""
        switch ($cat.Id) {
            13 {
                $base = $controllerItems[(Get-Random -Minimum 0 -Maximum $controllerItems.Count)]
                $mod = @("Negro", "Blanco", "Edicion Especial", "Inalambrico", "Color Clasico", "Gris Retro", "Pro Edition", "V2 Reacondicionado", "Custom Blue", "Custom Red")[(Get-Random -Minimum 0 -Maximum 10)]
                $name = "$base - $mod"
            }
            14 {
                $base = $hardwareItems[(Get-Random -Minimum 0 -Maximum $hardwareItems.Count)]
                $mod = @("Caja Completa", "Consola Sola", "Excelente Estado", "HDMI Mod", "Reacondicionada A+", "Region Free", "Pack 2 Mandos", "Edicion Especial")[(Get-Random -Minimum 0 -Maximum 8)]
                $name = "$base ($mod)"
            }
            15 {
                $base = $accessoryItems[(Get-Random -Minimum 0 -Maximum $accessoryItems.Count)]
                $mod = @("Premium 1.8m", "Blindado 2m", "Original OEM", "Gold Plated", "Alta Calidad", "Version Pro", "Pack Instalacion", "Universal")[(Get-Random -Minimum 0 -Maximum 8)]
                $name = "$base - $mod"
            }
            16 {
                $base = $collectibleItems[(Get-Random -Minimum 0 -Maximum $collectibleItems.Count)]
                $mod = @("Nuevo en Caja", "Edicion Limitada", "Version Japonesa", "Exclusivo", "Coleccion Retro", "Foil Special", "Numerado")[(Get-Random -Minimum 0 -Maximum 7)]
                $name = "$base ($mod)"
            }
            17 {
                $base = $merchItems[(Get-Random -Minimum 0 -Maximum $merchItems.Count)]
                $mod = @("Talla S", "Talla M", "Talla L", "Talla XL", "Edicion Vintage", "Oficial Licenciado", "Diseno Exclusivo")[(Get-Random -Minimum 0 -Maximum 7)]
                $name = "$base - $mod"
            }
            18 {
                $base = $memoryItems[(Get-Random -Minimum 0 -Maximum $memoryItems.Count)]
                $mod = @("Original", "Sellado", "Clase 10 A1", "High Speed", "Garantia 1 Ano", "Capacidad Maxima", "Version Pro")[(Get-Random -Minimum 0 -Maximum 7)]
                $name = "$base ($mod)"
            }
            default {
                $base = $gameTitles[(Get-Random -Minimum 0 -Maximum $gameTitles.Count)]
                $edition = $editions[(Get-Random -Minimum 0 -Maximum $editions.Count)]
                $variant = @("Vol. 1", "Vol. 2", "Parte 1", "Parte 2", "Edicion Especial", "Classic", "Cartucho", "Disco", "Completo")[(Get-Random -Minimum 0 -Maximum 9)]
                $name = "$base $variant - $edition"
            }
        }
        
        # Ensure length <= 95 characters
        if ($name.Length -gt 95) {
            $name = $name.Substring(0, 95).Trim()
        }
        
        # Make name unique if needed
        $uniqueName = $name
        if ($usedNames.Contains($uniqueName)) {
            $uniqueName = "$name #$subIndex"
            if ($uniqueName.Length -gt 95) {
                $uniqueName = $uniqueName.Substring(0, 95).Trim()
            }
        }
        
        # Format barcode / code: PRD-XXXXX
        $code = "PRD-" + ($codeIndex.ToString("D5"))
        while ($usedCodes.Contains($code)) {
            $codeIndex++
            $code = "PRD-" + ($codeIndex.ToString("D5"))
        }
        
        $usedCodes.Add($code) | Out-Null
        $usedNames.Add($uniqueName) | Out-Null
        
        $products.Add([PSCustomObject]@{
            Codigo = $code
            Nombre = $uniqueName
            Precio = $price
            Stock = $stock
            CategoriaId = $cat.Id
        })
        
        $codeIndex++
        $subIndex++
        $generatedForThisCat++
    }
}

Write-Host "Total de productos generados en memoria: $($products.Count)"

# Now build the SQL file with chunked INSERT statements
$sqlFile = "C:\proyecto_pos\SistemaPosFinal\insert_4000_productos.sql"
$chunkSize = 250
$sb = [System.Text.StringBuilder]::new()

[void]$sb.AppendLine("BEGIN;")
[void]$sb.AppendLine("-- Insertando 4000 productos de ejemplo")

for ($i = 0; $i -lt $products.Count; $i += $chunkSize) {
    $chunk = $products.GetRange($i, [Math]::Min($chunkSize, $products.Count - $i))
    [void]$sb.AppendLine("INSERT INTO productos (codigo, codigo_barras, nombre, precio, existencia, stock, categoria_id) VALUES")
    
    $valuesList = [System.Collections.Generic.List[string]]::new()
    foreach ($p in $chunk) {
        $escapedName = $p.Nombre.Replace("'", "''")
        $valuesList.Add("('$($p.Codigo)', '$($p.Codigo)', '$escapedName', $($p.Precio), $($p.Stock), $($p.Stock), $($p.CategoriaId))")
    }
    
    [void]$sb.AppendLine(($valuesList -join ",`n") + ";")
}

[void]$sb.AppendLine("COMMIT;")

[System.IO.File]::WriteAllText($sqlFile, $sb.ToString(), [System.Text.Encoding]::UTF8)
Write-Host "Archivo SQL generado en: $sqlFile (Tamano: $((Get-Item $sqlFile).Length / 1KB) KB)"
