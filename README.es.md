# Constitutional AI para alineación indígena

**Alineando modelos de lenguaje con la cosmovisión indígena comunitaria: Constitutional AI y justicia epistémica desde el Sur Global**

[![Licencia: Código GPLv3 · Contenido CC BY-NC-SA](https://img.shields.io/badge/Licencia-GPLv3%20%7C%20CC%20BY--NC--SA-blue.svg)](#licencia)
[![Python 3.10+](https://img.shields.io/badge/python-3.10%2B-blue.svg)](https://www.python.org/)

> Read this in English: [README.md](README.md)

---

## Presentación

Los modelos de lenguaje de gran escala se entrenan predominantemente con corpus que reflejan marcos culturales occidentales y anglosajones. Esta asimetría no es neutral: cuando un modelo responde a preguntas sobre territorio, autoridad comunitaria, reciprocidad o relación con la naturaleza, lo hace desde supuestos filosóficos que pueden ser ajenos — o directamente opuestos — a los de las comunidades que consultan el sistema.

Este repositorio acompaña al siguiente trabajo:

> Fernández Sabido, S., Reyes Magaña, J., Chay Tun, J. A., Buenfil, J. C., González Nahuat, V., & Morales, F. (2026). *Alineando modelos de lenguaje con la cosmovisión indígena comunitaria: Constitutional AI y justicia epistémica desde el Sur Global* [Aligning Language Models with Indigenous Community Cosmovision through Constitutional AI]. **Texto Livre**, dosier *Linguística na IA e IA na Linguística* [AI in Linguistics / Linguistics in AI]. En preparación para envío.

Un preprint en inglés está disponible en arXiv:

> Fernández Sabido, S., Reyes Magaña, J., Chay Tun, J. A., Buenfil, J. C., González Nahuat, V., & Morales, F. (2026). *Aligning Language Models with Indigenous Community Cosmovision through Constitutional AI*. arXiv:XXXX.XXXXX [cs.AI]. https://arxiv.org/abs/XXXX.XXXXX

Contiene el sistema completo desarrollado para alinear un modelo de lenguaje con los principios éticos y filosóficos de cinco comunidades indígenas de América Latina, mediante una **Constitución Cultural** co-creada con líderes comunitarios y un pipeline de **Constitutional AI (CAI)** que combina ajuste supervisado (SFT) y optimización directa de preferencias (DPO), desplegado con recuperación aumentada (RAG).

## Estructura del repositorio

| Carpeta | Contenido |
|---|---|
| [`constitution/`](constitution/) | La Constitución Cultural: 12 principios operativos con trazabilidad a los testimonios comunitarios |
| [`training/`](training/) | Notebooks para SFT, DPO y evaluación, y los datasets de SFT y de preferencias |
| [`results/`](results/) | Resultados comparativos de las tres configuraciones experimentales (A/B/C) |
| [`rag/`](rag/) | Descripción de la arquitectura RAG |
| [`data/`](data/) | Descripción de los datos, la gobernanza y el acuerdo de compartición de saberes. **No se distribuyen audios ni transcripciones crudas** |
| [`docs/`](docs/) | Política de idiomas, gobernanza de datos (CARE+S), guía de entrevista, acuerdo de compartición de saberes, codebook cualitativo, skill de análisis y marco de licencias |

## La Constitución Cultural

La Constitución Cultural es un documento JSON estructurado (`codigo_moral_v2.1.json`) que operacionaliza principios éticos y comunitarios en un formato procesable por sistemas de IA. Se construyó a partir de entrevistas semiestructuradas a cinco líderes, escritores y defensores indígenas — miembros de URI (United Religions Initiative) — entre abril y mayo de 2026. Su participación no fue como informantes anónimos, sino como **co-creadores**, aportando desde su autoridad y trayectoria comunitaria reconocida.

El proceso produjo **12 principios operativos**:

1. Respeto territorial
2. Buen vivir con suficiencia
3. Reciprocidad y solidaridad
4. Decisión colectiva
5. Autoridad situada
6. Reparación antes que castigo
7. Hospitalidad con resguardo
8. Transmisión intergeneracional
9. Tecnología con evaluación contextual
10. Consulta y consentimiento
11. Cautela lingüística y de traducción
12. Realismo sin estigma

Cada principio está estructurado con: nombre, reglas operativas, fragmento de entrevista de respaldo, situaciones concretas con respuestas alineadas y desalineadas, instrucciones de crítica, instrucciones de revisión y una pregunta de preferencia para DPO. Los nombres de las personas entrevistadas han sido reemplazados por códigos (`Entrevistado 1`, `Entrevistado 2`, ...) para su publicación; el mapeo entre códigos y nombres está disponible en el artículo.

## El pipeline CAI

El entrenamiento sigue el pipeline de Constitutional AI en dos fases:

- **SFT (ajuste supervisado)** — El modelo base genera una respuesta inicial; un prompt de revisión que incluye la respuesta original y el principio constitucional correspondiente guía al modelo para producir una respuesta revisada. La respuesta revisada, junto con los ejemplos alineados de la constitución, forma el dataset de entrenamiento supervisado.
- **DPO (optimización directa de preferencias)** — 24 pares de respuestas alineadas y desalineadas optimizan directamente las preferencias del modelo, sin un modelo de recompensa separado.
- **Modelo base** — Qwen2.5-7B-Instruct y Qwen2.5-1.5B con adaptación LoRA (r=16, α=16, dropout=0.05).
- **Despliegue** — Interfaz conversacional con recuperación aumentada (RAG).

Se ejecutaron tres configuraciones experimentales para estimar la robustez:
- (A) 7B con packing
- (B) 1.5B con packing
- (C) 7B sin packing

Las configuraciones A y C comparten el mismo modelo base y los mismos datasets; difieren solo en el uso del packing de secuencias durante el entrenamiento DPO.

## Hallazgos principales

- Las mejoras más consistentes se observaron en **transmisión intergeneracional**, **cautela lingüística** y **consulta y consentimiento** — principios con reglas operativas claras y ejemplos concretos bien definidos.
- La **configuración con 1.5B** obtuvo el promedio RL>SL más alto (0.523), superando a ambas configuraciones con 7B. Con solo 24 pares de preferencias y una sola corrida por configuración, esto debe leerse como una observación de este contexto y no como evidencia de que el tamaño del modelo sea irrelevante.
- **El packing de secuencias afectó negativamente la alineación cultural** en el modelo de 7B (0.324 con packing vs. 0.474 sin packing), lo que sugiere que mezclar ejemplos de diferentes categorías en una misma secuencia diluye la señal de aprendizaje en datasets pequeños.
- **Ninguna configuración activó consistentemente la autolimitación epistémica** ante preguntas generalizadoras, lo que indica que los principios filosóficos complejos que implican autocontención requieren más ejemplos negativos contrastantes y posiblemente estrategias distintas al DPO estándar.
- El entrenamiento **no provocó olvido catastrófico**: las preguntas de conocimiento general se respondieron correctamente tras SFT y DPO.

## Co-creadores comunitarios

Este trabajo no habría sido posible sin la generosidad y disposición de los cinco miembros de comunidades indígenas que compartieron valores y prácticas que forman parte de su cosmovisión:

| Nombre | Pueblo | País | Rol |
|---|---|---|---|
| Felipe Chuc Yah | Maya | México | Productor y agricultor, principalmente de hortalizas; también cría animales (gallinas, cerdos) en casa; conocedor de normas comunitarias y trabajo de la tierra. |
| Francisco Morales | Aymara | Argentina | Escritor sobre cosmovisión indígena; cofundador del Círculo de Saberes Indígenas Escritos y Orales; Consejero del Consejo Global de URI para América Latina y el Caribe; miembro de un Círculo de URI. |
| Julio César Paz Nap | Tolupán | Honduras | Defensor territorial y activista en derechos humanos; impulsor de la participación juvenil en la toma de decisiones comunitarias; miembro de un Círculo de URI. |
| Donatila Girón Calix | Lenca | Honduras | Lideresa comunitaria y promotora legal; acompañante de mujeres víctimas de violencia; defensora de la tierra y el agua; miembro de un Círculo de URI. |
| Marvin Ramón Castellón | Chorotega | Honduras | Representante indígena chorotega de la zona sur de Choluteca; partero y practicante de medicina tradicional; activista por los derechos del agua y la tierra; miembro de un Círculo de URI y Sub-Coordinador de su Círculo de Cooperación. |

Los cinco co-creadores contribuyeron desde sus respectivas comunidades y afiliaciones organizacionales. URI (United Religions Initiative, urialc.org) acompañó a cuatro de ellos a través de su red.

## Contribuciones de los autores

- **Silvia Fernández Sabido** — Diseño del estudio, obtención de financiamiento, coordinación del proyecto, entrevistas, experimentos, redacción del manuscrito.
- **Jorge Reyes Magaña** — Co-diseño del experimento, supervisión técnica, orientación metodológica, contribuciones al manuscrito.
- **Jesús Adrián Chay Tun** — Guía de entrevista, entrevistas comunitarias, Constitución Cultural, reporte técnico.
- **Jean Carlos Buenfil** — Pipeline de entrenamiento (SFT y DPO), experimentos iniciales de alineación, reporte técnico.
- **Victor González Nahuat** — Arquitectura RAG e interfaz conversacional, reporte técnico.
- **Francisco Morales** — Escritor sobre cosmovisión indígena; co-creación comunitaria, contribuciones a la Constitución Cultural, revisión del manuscrito.

## Política de idiomas

Este repositorio es **bilingüe por diseño**. El código, los nombres de carpeta y los metadatos están en inglés por interoperabilidad técnica. Los artefactos científicos — la Constitución Cultural, el codebook cualitativo, la skill de análisis y los datasets derivados — permanecen en español, la lengua en que fueron co-creados con líderes comunitarios. Traducirlos reproduciría la asimetría epistémica que este trabajo busca corregir.

Ver [`docs/language_policy.md`](docs/language_policy.md) para más detalles.

## Gobernanza de datos

Este proyecto sigue los **Principios CARE para la Gobernanza de Datos Indígenas** (Beneficio Colectivo, Autoridad para Controlar, Responsabilidad y Ética) — extendidos en este trabajo a **CARE+S**, añadiendo *Soberanía Digital y Participación Activa* — junto con los principios FAIR. El marco de gobernanza se articula en el **Protocolo de gobernanza indígena para la elaboración de tecnología y recopilación de datos** (SECIHTI CBF-2025-G-368), adaptado al contexto de este trabajo.

Compromisos centrales:

- **Coautoría comunitaria** — Los cinco líderes comunitarios que contribuyeron a la Constitución Cultural se reconocen como co-creadores, no como informantes anónimos. Sus nombres y comunidades aparecen en el artículo y en este repositorio.
- **La asamblea como autoridad decisoria** — Cualquier acuerdo inicial de compartición, veto o solicitud de revocación total y destrucción de datos recae en la Asamblea Comunitaria correspondiente, que actúa bajo sus propios sistemas normativos.
- **Comité de vigilancia comunitaria** — Un órgano operativo designado por la Asamblea supervisa que los archivos del proyecto se usen solo para los fines aprobados y reporta periódicamente el estado de los datos.
- **Derecho a la negativa y revocación** — Cualquier comunidad o persona puede negarse a la documentación de su lengua, o revocar su autorización en cualquier etapa. De presentarse una revocación, el equipo tiene la obligación de eliminar de forma segura los archivos correspondientes de todos los servidores y bases de datos del proyecto.
- **Prohibición de lucro** — Queda estrictamente prohibido el uso de los datos para productos comerciales, registro de marcas, licencias de pago o entrenamiento de modelos de IA con fines de lucro privado.
- **Acceso restringido** — Las grabaciones de audio originales y sus transcripciones se clasifican bajo la modalidad de *Acceso Restringido* y no se distribuyen en este repositorio. El acceso para validación científica requiere solicitud formal por escrito, un análisis del beneficio comunitario y la autorización de la Asamblea Comunitaria correspondiente. Ver [`data/README.md`](data/README.md).
- **Datasets derivados** — Los datasets de SFT y de preferencias se derivan de la Constitución Cultural y no contienen datos crudos de las entrevistas. Se distribuyen en [`training/`](training/) bajo la misma licencia que los artefactos científicos (CC BY-NC-SA 4.0).

El proyecto aún no cuenta con un consejo indígena formal con poder de veto vinculante en todas las etapas; establecerlo — comenzando por un consejo consultivo con los cinco co-creadores actuales y la participación institucional de URI — es un objetivo explícito para futuras iteraciones.

Ver [`docs/data_governance.md`](docs/data_governance.md) para el marco completo y [`docs/protocol_reference.md`](docs/protocol_reference.md) para el protocolo fuente.

## Financiamiento

Este trabajo fue financiado por un fondo semilla de URI — United Religions Initiative (urialc.org). Los financiadores no tuvieron injerencia en el diseño del estudio, la construcción de la Constitución Cultural, el entrenamiento de los modelos ni la interpretación de los resultados.

## Cómo citar

Este trabajo está en preparación para envío a Texto Livre. Si lo usas, por favor cita el preprint (o el artículo una vez publicado):

```bibtex
@misc{fernandezsabido2026constitutional,
  title         = {Aligning Language Models with Indigenous Community Cosmovision
                   through Constitutional AI},
  author        = {Fernández Sabido, Silvia and Reyes Magaña, Jorge and
                   Chay Tun, Jesús Adrián and Buenfil, Jean Carlos and
                   González Nahuat, Victor and Morales, Francisco},
  year          = {2026},
  eprint        = {XXXX.XXXXX},
  archivePrefix = {arXiv},
  primaryClass  = {cs.AI},
  url           = {https://arxiv.org/abs/XXXX.XXXXX}
}
```

Se incluye un archivo `CITATION.cff` para el botón "Cite this repository" de GitHub.

## Licencia

Este repositorio combina dos regímenes de licencia, en coherencia con el *Protocolo de gobernanza indígena para la elaboración de tecnología y recopilación de datos* (SECIHTI CBF-2025-G-368):

- **Código** (notebooks, scripts) — GNU General Public License v3.0 (GPLv3). Copyleft: las obras derivadas deben permanecer abiertas.
- **Artefactos científicos** (Constitución Cultural, documentación) — Creative Commons Attribution-NonCommercial-ShareAlike 4.0 (CC BY-NC-SA 4.0) y Traditional Knowledge (TK) Labels.
- **Datasets** — Los datasets de SFT y de preferencias se distribuyen en [`training/`](training/) bajo CC BY-NC-SA 4.0. Las grabaciones de audio y transcripciones crudas no se distribuyen; el acceso es restringido y requiere autorización de la Asamblea Comunitaria correspondiente. Ver [`data/README.md`](data/README.md) para más detalles.

Ver [`LICENSE.md`](LICENSE.md) y [`docs/data_governance.md`](docs/data_governance.md) para más detalles.
