<p align="center"><img src="https://raw.githubusercontent.com/SlimefunNewHorizons/DemonicExpansion/main/banner.svg" alt="DemonicExpansion" width="100%"></p>

# DemonicExpansion

> ### 🏰 ¡Únete a la Comunidad Oficial de DrakesCraft!
> 
> * 🎮 **IP del Servidor**: `play.drakescraft.net` *(Java 1.21.11 & Bedrock)*
> * 💬 **Discord Oficial**: [discord.gg/drakescraft](https://discord.gg/rv3vtXZTk7)
> * 🌐 **Web & Guía**: [drakescraft.net](https://drakescraft.net) — 🛒 **Tienda**: [tienda.drakescraft.net](https://tienda.drakescraft.net)
> 
> *¡Juega con este addon y más de 80 expansiones optimizadas en vivo en nuestra network de supervivencia técnica!*

---

Criaturas y equipo demoníaco para el Nether, adaptado al ecosistema Slimefun de **DrakesCraft**
(Paper/Purpur 1.21.11, Java 21).

## Qué añade

**Criaturas** que sustituyen a las vanilla al aparecer en el Nether: el Segador, el Vulcano, el
Sabueso Infernal y el Nigromante, cada uno con sus atributos, su equipo y su comportamiento al
golpear y al ser golpeado.

**Equipo demoníaco**: la armadura completa (casco, peto, grebas y botas), cada pieza con un efecto
mientras se lleva puesta — visión nocturna, resistencia al fuego, regeneración y un caminante de
lava que convierte en obsidiana la lava de alrededor.

**Objetos**: el Anillo Demoníaco, que ciega, debilita y prende fuego a los enemigos cercanos con
un enfriamiento de 60 segundos; la Moneda de Pentecostés, que fija un punto y te lleva al Nether
y de vuelta; el Corazón de Demonio, el Napalm y un generador que solo produce sobre lava.

## Qué cambiamos

Este repositorio **no es un fork**: es el código original integrado en el ecosistema de
DrakesCraft. Los cambios son estos.

**La librería del autor ya no existía.** El addon dependía de `SmartPlugin`, de TheSilentPro,
resuelta por jitpack. Ese repositorio devuelve 404 y jitpack devuelve 401: no hay jar, no hay
fuente y no queda copia en ninguna caché, así que el addon simplemente no compilaba. Está
reimplementada en `cl.jackstar.smartplugin`, **dentro de este mismo jar**, cubriendo solo la
superficie que el addon usaba: 8 utilidades y 7 tipos base. No hace falta ningún jar aparte.

**Fuera el autoactualizador.** El original se descargaba el jar más reciente de un repositorio
ajeno y se reemplazaba a sí mismo al arrancar. En un servidor en producción eso no es una
comodidad: es que cualquier cambio de allá llega aquí sin que nadie lo revise, y además pisaría
los arreglos que hemos hecho nosotros.

**Al día con 1.21.11.** Los paquetes de Slimefun pasan a `com.github.drakescraft_labs`, que es
como está repaquetado nuestro core. Los atributos perdieron el prefijo `GENERIC_` en 1.21.3 y
`Enchantment.DAMAGE_ALL` pasó a llamarse `SHARPNESS`. Se compila contra `paper-api` 1.21.11, la
misma versión que corre en producción, para que ninguna de esas referencias reviente al arrancar.

**Todo en español.** El fork del que partimos tenía el catálogo en chino; nombres, descripciones
y mensajes están traducidos.

**Ticker de armadura acotado.** El original recorría cada ser vivo de todos los mundos cada tick,
incluidos animales de granjas y mobs de chunks cargados. Ahora solo procesa jugadores conectados
cada 5 ticks. El caminante de lava recalcula el terreno únicamente cuando el jugador cambia de
bloque, desactiva física innecesaria y limita su radio configurable a 8 para evitar hasta 40.401
consultas de bloques y protecciones por paso. El radio normal sigue siendo 2 y los demás efectos
se renuevan con la misma continuidad visual.

**Registro de criaturas por eventos.** El ticker de entidades ya no busca identificadores PDC en
todos los mobs 20 veces por segundo. Las criaturas demoníacas se registran al generarse o cargar
su chunk, se retiran al morir o descargarse y solo ellas reciben su actualización periódica.

**Vulcanes protegidos contra avalanchas.** Solo los magma cubes de aparición natural pueden
convertirse en Vulcanes; las apariciones `CUSTOM`, de trial spawner y los descendientes
`SLIME_SPLIT` quedan fuera. Los Vulcanes no se dividen ni participan en colisiones entre
entidades, y al cargar un chunk se aplica la misma protección a ejemplares antiguos identificados
por PDC o por su nombre legado. Así se conserva la criatura sin borrar mobs ni multiplicar el
coste de colisiones del hilo principal.

**Clásico permanece vanilla.** Los mundos reservados de Clásico nunca sustituyen criaturas
naturales por entidades demoníacas. Esta exclusión se aplica también cuando `config.yml` está
vacío o procede de una versión anterior; `entity-replacements.excluded-worlds` permite ampliar
la lista para otros mundos, pero no retirar la protección de Clásico.

El paquete propio del addon y sus nombres de clase se dejan intactos, para poder seguir comparando
con el original.

## Instalación

Necesita Slimefun de DrakesCraft (`Slimefun4-Drake`). Se pone el jar en `plugins/` y listo.
El build usa Java 21 y declara explícitamente el procesador de anotaciones de Lombok, por lo que
también es reproducible desde Maven ejecutado sobre JDK recientes.

## Crédito

El trabajo de fondo es de **TheSilentPro (Silent)**. Nosotros solo lo hemos adaptado. Los detalles
de procedencia y licencia están en [UPSTREAM.md](UPSTREAM.md).

---

## 📄 License & Upstream Attribution

This project is a sovereign fork maintained by [**JackStar6677-1**](https://github.com/JackStar6677-1) under [**DrakesCraft Labs**](https://github.com/SlimefunNewHorizons).

- **Original Project:** Created by the upstream authors and the open-source community.
- **DrakesCraft Optimizations:** Modernized for Paper/Purpur 1.21.11+, Java 21, high concurrency, asynchronous safety, and exploit/duplication prevention.
- **License:** Distributed under the original **GNU General Public License v3.0 (GPLv3)** (or original upstream license). See the [LICENSE](LICENSE) file for complete terms.
