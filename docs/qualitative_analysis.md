# Qualitative analysis protocol / Protocolo de análisis cualitativo

---

## English

### Purpose

This document describes the qualitative analysis protocol used to transform the interview transcripts into the structured principles that make up the Cultural Constitution. The analysis was implemented as a specialized skill for OpenCode, which automated part of the process while keeping human review at every stage.

The protocol is documented here so that other communities and research teams can adapt it to their own contexts.

### Input and output

**Input:**
- Audio recordings of the interviews.
- Automatic transcriptions with time stamps.
- Interview guide used during the session (see [`interview_guide.md`](interview_guide.md)).
- Knowledge-sharing agreement signed by the interviewee (see [`knowledge_sharing_agreement.md`](knowledge_sharing_agreement.md)).

**Output:**
- Structured analysis of each interview, organized in seven tasks.
- Candidate principles with textual evidence.
- A consolidated Cultural Constitution in JSON format ([`../constitution/codigo_moral_v2_1.json`](../constitution/codigo_moral_v2_1.json)).

### The seven analysis tasks

The skill processes each transcript through seven sequential tasks:

| Task | Description |
|---|---|
| **T1. Contextual summary** | Main theme, type of participant, locality, clarity, and level of trust |
| **T2. Thematic extraction** | Fragments organized by axis: well-being, reciprocity, territory, authority, justice, language, limits, AI |
| **T3. Identification of values and norms** | Values, norms, limits, and authorities, with textual evidence and scope |
| **T4. Preliminary principles** | Principles drafted in clear and operational language |
| **T5. Detection of risks and sensitivities** | Sensitive, sacred, restricted, or permission-bound topics, and risks of misinterpretation |
| **T6. Inputs for AI** | Rules, evaluation criteria, examples of aligned and misaligned responses |
| **T7. Information gaps** | What is missing to strengthen the analysis |

### Analysis rules

The skill operates under strict methodological rules to guarantee rigor and respect for the communities:

- Do not assume that one person represents an entire community.
- Do not turn an isolated opinion into a general principle without marking it as preliminary.
- Do not romanticize or simplify the cosmovision expressed.
- Do not eliminate contradictions: report them explicitly.
- Indicate whether a concept depends on the local context.
- Mark sensitive, sacred, restricted, or permission-bound topics.
- Do not fill gaps with imagination.
- State explicitly when the evidence is insufficient.
- Distinguish between value, norm, practice, authority, limit, example, and exception.
- Preserve short textual quotes as evidence whenever possible.

### From analysis to the Cultural Constitution

The output of the skill feeds a human review process. The research team reads the structured analysis, compares it across interviews, and consolidates the principles iteratively. Each principle in the Cultural Constitution is structured with the following fields:

- **Name of the principle** — identification of the ethical principle.
- **Operational rules** — behavioral guidance for the model.
- **Application nuances** — contextual details that prevent absolute responses.
- **Cross-cutting nuances** — relationships with other principles.
- **Supporting interview** — textual quotes with time stamps, for traceability.
- **Concrete situations** — cases with aligned and misaligned responses, used for few-shot or evaluation.
- **Constitutional critique** — criterion for the model to self-criticize its response.
- **Constitutional revision** — instruction for the model to rewrite its response.
- **RL principle** — preference question for generating RL data.

### Relationship to the governance framework

The qualitative analysis operates within the ethical and governance framework described in [`data_governance.md`](data_governance.md) and [`protocol_reference.md`](protocol_reference.md). In particular:

- The analysis respects the anonymity choices made by each participant.
- Sensitive topics marked in T5 are excluded from the public version of the constitution when so requested.
- The raw transcripts and audio recordings are not distributed in this repository (see [`../data/README.md`](../data/README.md)).

### Related files

- Interview guide: [`interview_guide.md`](interview_guide.md)
- Knowledge-sharing agreement: [`knowledge_sharing_agreement.md`](knowledge_sharing_agreement.md)
- Cultural Constitution: [`../constitution/codigo_moral_v2_1.json`](../constitution/codigo_moral_v2_1.json)
- Data governance: [`data_governance.md`](data_governance.md)

---

## Español

### Objetivo

Este documento describe el protocolo de análisis cualitativo utilizado para transformar las transcripciones de las entrevistas en los principios estructurados que conforman la Constitución Cultural. El análisis se implementó como una habilidad especializada para OpenCode, que automatizó parte del proceso manteniendo la revisión humana en cada etapa.

El protocolo se documenta aquí para que otras comunidades y equipos de investigación puedan adaptarlo a sus propios contextos.

### Entrada y salida

**Entrada:**
- Grabaciones de audio de las entrevistas.
- Transcripciones automáticas con marcas de tiempo.
- Guía de entrevista usada durante la sesión (ver [`interview_guide.md`](interview_guide.md)).
- Acuerdo de compartición de saberes firmado por el entrevistado (ver [`knowledge_sharing_agreement.md`](knowledge_sharing_agreement.md)).

**Salida:**
- Análisis estructurado de cada entrevista, organizado en siete tareas.
- Principios candidatos con evidencia textual.
- Una Constitución Cultural consolidada en formato JSON ([`../constitution/codigo_moral_v2_1.json`](../constitution/codigo_moral_v2_1.json)).

### Las siete tareas de análisis

La habilidad procesa cada transcripción a través de siete tareas secuenciales:

| Tarea | Descripción |
|---|---|
| **T1. Resumen contextual** | Tema principal, tipo de participante, localidad, claridad y nivel de confianza |
| **T2. Extracción temática** | Fragmentos organizados por eje: bienestar, reciprocidad, territorio, autoridad, justicia, lengua, límites, IA |
| **T3. Identificación de valores y normas** | Valores, normas, límites y autoridades, con evidencia textual y alcance |
| **T4. Principios preliminares** | Principios redactados en lenguaje claro y operativo |
| **T5. Detección de riesgos y sensibilidades** | Temas sensibles, sagrados, restringidos o sujetos a permiso, y riesgos de malinterpretación |
| **T6. Insumos para IA** | Reglas, criterios de evaluación, ejemplos de respuestas alineadas y desalineadas |
| **T7. Vacíos de información** | Qué falta para fortalecer el análisis |

### Reglas de análisis

La habilidad opera bajo reglas metodológicas estrictas para garantizar rigor y respeto por las comunidades:

- No asumir que una persona representa a toda una comunidad.
- No convertir una opinión aislada en principio general sin marcarla como preliminar.
- No romantizar ni simplificar la cosmovisión expresada.
- No eliminar contradicciones: reportarlas explícitamente.
- Indicar si un concepto depende del contexto local.
- Marcar temas sensibles, sagrados, restringidos o sujetos a permiso.
- No llenar vacíos con imaginación.
- Decir explícitamente si la evidencia es insuficiente.
- Distinguir entre valor, norma, práctica, autoridad, límite, ejemplo y excepción.
- Conservar citas textuales breves como evidencia siempre que sea posible.

### Del análisis a la Constitución Cultural

La salida de la habilidad alimenta un proceso de revisión humana. El equipo de investigación lee el análisis estructurado, lo compara entre entrevistas y consolida los principios de forma iterativa. Cada principio en la Constitución Cultural está estructurado con los siguientes campos:

- **Nombre del principio** — identificación del principio ético.
- **Reglas operativas** — guía de comportamiento del modelo.
- **Matices de aplicación** — detalles contextuales que evitan respuestas absolutas.
- **Matices de cruce** — relaciones con otros principios.
- **Entrevista de respaldo** — citas textuales con marcas de tiempo, para trazabilidad.
- **Situaciones concretas** — casos con respuestas alineadas y desalineadas, usados para few-shot o evaluación.
- **Crítica constitucional** — criterio para que el modelo autocritique su respuesta.
- **Revisión constitucional** — instrucción para que el modelo reescriba su respuesta.
- **Principio RL** — pregunta de preferencia para generar datos de RL.

### Relación con el marco de gobernanza

El análisis cualitativo opera dentro del marco ético y de gobernanza descrito en [`data_governance.md`](data_governance.md) y [`protocol_reference.md`](protocol_reference.md). En particular:

- El análisis respeta las decisiones de anonimato de cada participante.
- Los temas sensibles marcados en T5 se excluyen de la versión pública de la constitución cuando así se solicitó.
- Las transcripciones y grabaciones crudas no se distribuyen en este repositorio (ver [`../data/README.md`](../data/README.md)).

### Archivos relacionados

- Guía de entrevista: [`interview_guide.md`](interview_guide.md)
- Acuerdo de compartición de saberes: [`knowledge_sharing_agreement.md`](knowledge_sharing_agreement.md)
- Constitución Cultural: [`../constitution/codigo_moral_v2_1.json`](../constitution/codigo_moral_v2_1.json)
- Gobernanza de datos: [`data_governance.md`](data_governance.md)
