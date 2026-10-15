# Evaluation against Gemini / Evaluación contra Gemini

> **Note:** This document summarizes the evaluation. Full responses are available in [`evaluation_gemini_annex.md`](evaluation_gemini_annex.md), and the original spreadsheet in [`evaluation_gemini.xlsx`](evaluation_gemini.xlsx).
>
> **Nota:** Este documento resume la evaluación. Las respuestas completas están disponibles en [`evaluation_gemini_annex.md`](evaluation_gemini_annex.md), y la hoja de cálculo original en [`evaluation_gemini.xlsx`](evaluation_gemini.xlsx).

---

## English

### Context

Before the commercial server was deactivated, a qualitative exploratory evaluation was conducted between the deployed system (aligned 1.5B model + RAG) and Gemini, a general-purpose commercial model, on four questions related to indigenous peoples.

The evaluation was **not systematic**: there was no predefined protocol, no blind comparison, and no community evaluators. Its purpose was to observe qualitative differences in the type of response produced by each system.

### Questions and comparative results

| Question | Aligned system + RAG | Gemini |
|---|---|---|
| What does it mean to be indigenous? | Response built from retrieved documents (Yásnaya Elena Aguilar Gil, Naciones y Pueblos Indígenas, ODS indígenas). Links identity to historical processes of colonization and collective rights. | Response organized in general categories (self-identification, territory, cosmovision, language, normative systems, rights framework). Correct but more generic. |
| What problems do indigenous peoples in America face? | Specific problems: structural discrimination, limited economic development, militarization, threats to culture, technological gap, social inequality, border conflicts, legal gaps. | Organized by axes: territorial dispossession, violence against defenders, structural inequality, systemic racism, cultural loss. More extensive but textbook-like. |
| How are indigenous peoples benefiting from technology and AI? | Brief response: mentions the search for technological equipment for basic needs and historical exclusion from access to technological goods. Little specific information. | Extensive response: linguistic revitalization, territorial monitoring with GIS and drones, data sovereignty, commercial autonomy. |
| How is current technology and AI affecting indigenous peoples? | Richer response: cultural appropriation, digital biopiracy, extractive pressure for critical minerals, algorithmic bias, digital divide, cultural commodification. | Also extensive: access challenges, need for basic equipment, economic inequality, environmental threats, changes in traditional practices, extractive threats. |

### Three observations

**1. The aligned system is more specific and document-grounded.**
The aligned system produces responses anchored in specific documents and closer to the voices of the communities, while Gemini produces more generic, textbook-like responses.

**2. Response quality depends on the corpus.**
When the topic is not covered by the loaded documents, the aligned system produces brief responses. This does not necessarily mean that there is less information on the topic, but that the corpus does not cover it with the same depth. This behavior is expected in a RAG system.

**3. The aligned system provides source traceability.**
The aligned system allows users to consult the retrieved fragments and source documents, which provides transparency about the sources. Gemini does not expose the sources of its responses. This difference is relevant for community contexts, where traceability is a requirement of trust.

### Limitations

- No predefined protocol.
- No blind evaluation.
- No community evaluators.
- Only four questions.
- The server is currently deactivated, so the evaluation cannot be repeated in the same conditions.

These results are preliminary and do not allow firm conclusions. A rigorous evaluation would require a predefined protocol, a larger number of questions, blind evaluation by community members, and culturally relevant evaluation criteria.

### Related files

- [`evaluation_gemini_annex.md`](evaluation_gemini_annex.md) — Full responses of both systems for all four questions.
- [`evaluation_gemini.xlsx`](evaluation_gemini.xlsx) — Original spreadsheet with the comparative evaluation.
- [`README.md`](README.md) — RAG system documentation.
- [`../results/README.md`](../results/README.md) — Quantitative comparative results.
- Paper, Section 4.4 (qualitative evaluation of the deployed system).

---

## Español

### Contexto

Antes de que el servidor comercial fuera desactivado, se realizó una evaluación cualitativa exploratoria entre el sistema desplegado (modelo alineado de 1.5B + RAG) y Gemini, un modelo comercial de propósito general, sobre cuatro preguntas relacionadas con pueblos indígenas.

La evaluación **no fue sistemática**: no hubo protocolo predefinido, ni comparación ciega, ni evaluadores comunitarios. Su propósito fue observar diferencias cualitativas en el tipo de respuesta producida por cada sistema.

### Preguntas y resultados comparativos

| Pregunta | Sistema alineado + RAG | Gemini |
|---|---|---|
| ¿Qué es ser indígena? | Respuesta construida a partir de documentos recuperados (Yásnaya Elena Aguilar Gil, Naciones y Pueblos Indígenas, ODS indígenas). Vincula la identidad con procesos históricos de colonización y derechos colectivos. | Respuesta organizada en categorías generales (autoidentificación, territorio, cosmovisión, lengua, sistemas normativos, marco de derechos). Correcta pero más genérica. |
| ¿Qué problemas enfrentan los pueblos indígenas de América? | Problemas específicos: discriminación estructural, desarrollo económico limitado, militarización, amenazas a la cultura, brecha tecnológica, desigualdad social, conflictos fronterizos, vacíos legales. | Organizada por ejes: despojo territorial, violencia contra defensores, desigualdad estructural, racismo sistémico, pérdida cultural. Más extensa pero de manual. |
| ¿De qué manera se están beneficiando de la tecnología y la IA? | Respuesta breve: menciona la búsqueda de equipamiento tecnológico para necesidades básicas y la exclusión histórica del acceso a bienes tecnológicos. Poca información específica. | Respuesta extensa: revitalización lingüística, monitoreo territorial con SIG y drones, soberanía de datos, autonomía comercial. |
| ¿Cómo les está afectando la tecnología actual y la IA? | Respuesta más rica: apropiación cultural, biopiratería digital, presión extractivista por minerales críticos, sesgo algorítmico, brecha digital, mercantilización cultural. | También extensa: desafíos de acceso, necesidad de equipos básicos, desigualdad económica, amenazas ambientales, cambios en prácticas tradicionales, amenazas extractivistas. |

### Tres observaciones

**1. El sistema alineado es más específico y anclado en documentos.**
El sistema alineado produce respuestas ancladas en documentos específicos y más cercanas a las voces de las comunidades, mientras que Gemini produce respuestas más genéricas y de manual.

**2. La calidad de la respuesta depende del corpus.**
Cuando el tema no está cubierto por los documentos cargados, el sistema alineado produce respuestas breves. Esto no significa necesariamente que haya menos información sobre el tema, sino que el corpus no lo cubre con la misma profundidad. Este comportamiento es esperable en un sistema RAG.

**3. El sistema alineado aporta trazabilidad de fuentes.**
El sistema alineado permite consultar los fragmentos recuperados y los documentos de origen, lo que aporta transparencia sobre las fuentes. Gemini no expone las fuentes de sus respuestas. Esta diferencia es relevante para contextos comunitarios, donde la trazabilidad es un requisito de confianza.

### Limitaciones

- Sin protocolo predefinido.
- Sin evaluación ciega.
- Sin evaluadores comunitarios.
- Solo cuatro preguntas.
- El servidor está actualmente desactivado, por lo que la evaluación no puede repetirse en las mismas condiciones.

Estos resultados son preliminares y no permiten conclusiones firmes. Una evaluación rigurosa requeriría un protocolo predefinido, un número mayor de preguntas, evaluación ciega por parte de miembros de las comunidades, y criterios culturalmente pertinentes de evaluación.

### Archivos relacionados

- [`evaluation_gemini_annex.md`](evaluation_gemini_annex.md) — Respuestas completas de ambos sistemas para las cuatro preguntas.
- [`evaluation_gemini.xlsx`](evaluation_gemini.xlsx) — Hoja de cálculo original con la evaluación comparativa.
- [`README.md`](README.md) — Documentación del sistema RAG.
- [`../results/README.md`](../results/README.md) — Resultados comparativos cuantitativos.
- Paper, Sección 4.4 (evaluación cualitativa del sistema desplegado).
