# Language policy / Política de idiomas

---

## English

### Design principle

This repository is **bilingual by design**. The choice of language for each type of material is not accidental: it reflects both technical interoperability needs and an explicit commitment to epistemic justice.

### Distribution of languages

| Material | Language | Rationale |
|---|---|---|
| Code (variables, comments, docstrings) | English | Technical standard; enables collaboration and review |
| Folder names, file names | English | Standard convention; improves discoverability |
| Metadata (`CITATION.cff`, `LICENSE.md`) | English | GitHub and citation conventions |
| `README.md` (main) | English | Entry point for the international audience reached via the arXiv preprint |
| Cultural Constitution (`codigo_moral_v2_1.json`) | Spanish | Scientific artifact co-created with community leaders; translating it would decontextualize it |
| Prompts, datasets, evaluation criteria | Spanish | Same reason: they are the artifacts of the research, not the scaffolding |
| Community co-creator names and roles | As each person identifies | Respect for how each person names themselves |
| `docs/` files | Bilingual (EN + ES in the same file) | To serve both technical and community audiences without duplicating files |

### Why this distribution

Translating the Cultural Constitution, the prompts, or the preference datasets into English would reproduce the very epistemic asymmetry this work seeks to address. These artifacts were co-created in Spanish with community leaders; they carry the language, the nuances, and the philosophical positions of the people who authored them. Translating them would strip them of that context.

Code, by contrast, follows English conventions because that is the lingua franca of software collaboration. Making it Spanish-only would isolate the project from technical review and reuse.

This distribution is therefore not a compromise; it is a deliberate alignment between language and function.

### A pending goal: indigenous languages

An ideal version of this repository would include materials in **all the indigenous languages of the participating communities**: Maya, Aymara, Tolupán, Lenca, and Chorotega. This was not possible at this stage, for several reasons:

- The Cultural Constitution was co-created in Spanish, the common language of the interview process.
- Producing high-quality materials in five indigenous languages would require translation, validation, and community review processes beyond the current resources and timeline.
- Doing it partially or hastily would risk reproducing the same extractive dynamics the project criticizes.

Making this possible is an explicit goal for future iterations. The path would involve:

1. Translating the Cultural Constitution and key prompts into the languages of the participating communities.
2. Validating those translations with community members, not external translators.
3. Publishing them alongside the Spanish originals, not replacing them.
4. Recognizing the translators as co-authors of the translated versions.

Until then, this section documents both the intention and the honest acknowledgment that it remains unfulfilled.

### How to contribute translations

If you wish to contribute a translation of any material in this repository:

1. Open an issue describing what you want to translate and into which language.
2. Do not translate the Cultural Constitution, prompts, or datasets without prior authorization from the corresponding Community Assembly (see [`data_governance.md`](data_governance.md)).
3. Documentation files (like this one) can be translated more freely, but please open an issue first to coordinate.
4. Translated versions must be published alongside the original, not as replacements.
5. Translators will be acknowledged as co-authors of the translated version.

---

## Español

### Principio de diseño

Este repositorio es **bilingüe por diseño**. La elección del idioma para cada tipo de material no es accidental: refleja tanto necesidades de interoperabilidad técnica como un compromiso explícito con la justicia epistémica.

### Distribución de idiomas

| Material | Idioma | Razón |
|---|---|---|
| Código (variables, comentarios, docstrings) | Inglés | Estándar técnico; facilita la colaboración y la revisión |
| Nombres de carpetas y archivos | Inglés | Convención estándar; mejora la descubribilidad |
| Metadatos (`CITATION.cff`, `LICENSE.md`) | Inglés | Convenciones de GitHub y de citación |
| `README.md` (principal) | Inglés | Puerta de entrada para el público internacional que llega vía el preprint en arXiv |
| Constitución Cultural (`codigo_moral_v2_1.json`) | Español | Artefacto científico co-creado con líderes comunitarios; traducirlo lo descontextualizaría |
| Prompts, datasets, criterios de evaluación | Español | Misma razón: son los artefactos de la investigación, no el andamiaje |
| Nombres y roles de co-creadores comunitarios | Como cada persona se identifica | Respeto a cómo cada persona se nombra a sí misma |
| Archivos de `docs/` | Bilingüe (EN + ES en el mismo archivo) | Para servir a públicos técnicos y comunitarios sin duplicar archivos |

### Por qué esta distribución

Traducir la Constitución Cultural, los prompts o los datasets de preferencias al inglés reproduciría la misma asimetría epistémica que este trabajo busca corregir. Estos artefactos fueron co-creados en español con líderes comunitarios; cargan la lengua, los matices y las posiciones filosóficas de quienes los autorizaron. Traducirlos los despojaría de ese contexto.

El código, en cambio, sigue convenciones en inglés porque es la lengua franca de la colaboración en software. Hacerlo solo en español aislaría al proyecto de la revisión y la reutilización técnica.

Esta distribución no es por tanto un compromiso a medias; es una alineación deliberada entre lengua y función.

### Un objetivo pendiente: las lenguas indígenas

Una versión ideal de este repositorio incluiría materiales en **todas las lenguas indígenas de las comunidades participantes**: maya, aymara, tolupán, lenca y chorotega. Esto no fue posible en esta etapa, por varias razones:

- La Constitución Cultural fue co-creada en español, la lengua común del proceso de entrevistas.
- Producir materiales de alta calidad en cinco lenguas indígenas requeriría procesos de traducción, validación y revisión comunitaria más allá de los recursos y el tiempo actuales.
- Hacerlo de forma parcial o apresurada reproduciría las mismas dinámicas extractivas que el proyecto critica.

Hacerlo posible es un objetivo explícito para futuras iteraciones. La ruta implicaría:

1. Traducir la Constitución Cultural y los prompts clave a las lenguas de las comunidades participantes.
2. Validar esas traducciones con miembros de las comunidades, no con traductores externos.
3. Publicarlas junto a los originales en español, no en su lugar.
4. Reconocer a los traductores como co-autores de las versiones traducidas.

Hasta entonces, esta sección documenta tanto la intención como el reconocimiento honesto de que permanece sin cumplir.

### Cómo contribuir traducciones

Si deseas contribuir una traducción de cualquier material de este repositorio:

1. Abre un issue describiendo qué quieres traducir y a qué lengua.
2. No traduzcas la Constitución Cultural, los prompts ni los datasets sin autorización previa de la Asamblea Comunitaria correspondiente (ver [`data_governance.md`](data_governance.md)).
3. Los archivos de documentación (como este) se pueden traducir con más libertad, pero abre un issue primero para coordinar.
4. Las versiones traducidas deben publicarse junto al original, no como reemplazo.
5. Los traductores serán reconocidos como co-autores de la versión traducida.
