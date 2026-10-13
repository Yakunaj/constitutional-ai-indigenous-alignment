# Data / Datos

---

## English

### Notice

This repository distributes **derived scientific artifacts** — the Cultural Constitution, prompts, preference datasets, and comparative results — but **not the original audio recordings or their literal transcriptions**. The latter are the collective intellectual and cultural property of the participating indigenous communities and remain under restricted access, governed by the corresponding Community Assembly.

This distinction follows the *Indigenous Governance Protocol for Technology Development and Data Collection* (SECIHTI CBF-2025-G-368).

### What is distributed in this repository

| Material | Location | License |
|---|---|---|
| Cultural Constitution (12 operational principles) | [`../constitution/codigo_moral_v2_1.json`](../constitution/codigo_moral_v2_1.json) | CC BY-NC-SA 4.0 |
| Constitutional critique prompts | [`../prompts/`](../prompts/) | CC BY-NC-SA 4.0 |
| Evaluation prompts | [`../prompts/`](../prompts/) | CC BY-NC-SA 4.0 |
| SFT dataset (derived from the constitution's aligned examples) | [`../data/`](../data/) | CC BY-NC-SA 4.0 |
| DPO preference pairs (24 aligned/misaligned responses) | [`../data/`](../data/) | CC BY-NC-SA 4.0 |
| Training notebooks (SFT, DPO, evaluation) | [`../training/`](../training/) | GPLv3 |
| Comparative results across configurations | [`../results/`](../results/) | CC BY-NC-SA 4.0 |
| RAG architecture description | [`../rag/`](../rag/) | GPLv3 |

These materials are **derived artifacts**: they operationalize the principles co-created with community leaders, but do not reproduce the original interviews or enable re-identification of participants.

### What is not distributed

| Material | Reason |
|---|---|
| Original audio recordings | Biometric data and collective intellectual property; classified as Restricted Access |
| Literal transcriptions of interviews | Same reason; their free diffusion violates privacy, personal data protection, and collective intellectual property rights |

These materials remain under the custody of the project team and are subject to the signed knowledge-sharing agreements and the SECIHTI Specific Guidelines for the Integration and Operation of Repositories (DOF, 06/09/2024).

### Conditions for derived datasets

The SFT dataset and DPO preference pairs are distributed under the following conditions:

- They do **not** contain literal fragments from the interviews beyond brief, attributed quotes already present in the paper.
- They do **not** enable re-identification of any participant.
- They are **operational examples** derived from the 12 principles, not raw testimonies.
- Any use for commercial products, trademark registration, paid licensing, or training AI models for private profit is **strictly prohibited**.

### How to request access to restricted data

Access to original audio recordings or literal transcriptions for scientific validation requires explicit authorization. The procedure is:

1. **Submit a formal written request** justifying the intended use, accompanied by an analysis of the benefit for the community.
2. **Alignment with Assembly criteria** — The proposed use and resulting benefits must be analyzed or adjusted according to the real, pertinent, and relevant needs of the community.
3. **Authorization from the Community Assembly** — The Assembly holds the right of veto over its data.
4. **Temporary credentials** — Once approved, temporary credentials and restricted access passwords are assigned.
5. **Limited scope** — Access is limited to a defined period and a specific set of files.
6. **No redistribution** — Copying, redistributing, or permanently storing files outside the authorized environment is prohibited.
7. **Final report** — A final report on the use of the data must be submitted.
8. **Return of results** — Results must be returned in a format adjusted to the community audience.
9. **Compliance** — Any breach of the protocol results in immediate revocation of access and possible legal action.

See [`../docs/data_governance.md`](../docs/data_governance.md) for the full framework.

### Contact

For data access requests or questions about the datasets distributed here, please contact the project's corresponding author:

> **Silvia Fernández Sabido** — SECIHTI / CentroGeo, Mérida, Yucatán, México

Requests for restricted data will be reviewed with the corresponding Community Assembly. Response times depend on the Assembly's own timelines.

---

## Español

### Aviso

Este repositorio distribuye **artefactos científicos derivados** — la Constitución Cultural, los prompts, los datasets de preferencias y los resultados comparativos — pero **no las grabaciones de audio originales ni sus transcripciones literales**. Estas últimas son propiedad intelectual y cultural colectiva de las comunidades indígenas participantes y permanecen bajo acceso restringido, gobernadas por la Asamblea Comunitaria correspondiente.

Esta distinción sigue el *Protocolo de gobernanza indígena para la elaboración de tecnología y recopilación de datos* (SECIHTI CBF-2025-G-368).

### Qué se distribuye en este repositorio

| Material | Ubicación | Licencia |
|---|---|---|
| Constitución Cultural (12 principios operativos) | [`../constitution/codigo_moral_v2_1.json`](../constitution/codigo_moral_v2_1.json) | CC BY-NC-SA 4.0 |
| Prompts de crítica constitucional | [`../prompts/`](../prompts/) | CC BY-NC-SA 4.0 |
| Prompts de evaluación | [`../prompts/`](../prompts/) | CC BY-NC-SA 4.0 |
| Dataset SFT (derivado de los ejemplos alineados de la constitución) | [`../data/`](../data/) | CC BY-NC-SA 4.0 |
| Pares de preferencias DPO (24 respuestas alineadas/desalineadas) | [`../data/`](../data/) | CC BY-NC-SA 4.0 |
| Notebooks de entrenamiento (SFT, DPO, evaluación) | [`../training/`](../training/) | GPLv3 |
| Resultados comparativos entre configuraciones | [`../results/`](../results/) | CC BY-NC-SA 4.0 |
| Descripción de la arquitectura RAG | [`../rag/`](../rag/) | GPLv3 |

Estos materiales son **artefactos derivados**: operacionalizan los principios co-creados con los líderes comunitarios, pero no reproducen las entrevistas originales ni permiten reidentificar a las personas participantes.

### Qué no se distribuye

| Material | Razón |
|---|---|
| Grabaciones de audio originales | Datos biométricos y propiedad intelectual colectiva; clasificados como Acceso Restringido |
| Transcripciones literales de las entrevistas | Misma razón; su libre difusión vulnera la privacidad, la protección de datos personales y la propiedad intelectual colectiva |

Estos materiales permanecen bajo custodia del equipo del proyecto y están sujetos a los acuerdos de compartición de saberes firmados y a los Lineamientos Específicos para la Integración y Operación de los Repositorios de la SECIHTI (DOF, 06/09/2024).

### Condiciones para los datasets derivados

El dataset SFT y los pares de preferencias DPO se distribuyen bajo las siguientes condiciones:

- **No** contienen fragmentos literales de las entrevistas más allá de citas breves y atribuidas ya presentes en el artículo.
- **No** permiten reidentificar a ninguna persona participante.
- Son **ejemplos operativos** derivados de los 12 principios, no testimonios crudos.
- Queda **estrictamente prohibido** cualquier uso para productos comerciales, registro de marcas, licencias de pago o entrenamiento de modelos de IA con fines de lucro privado.

### Cómo solicitar acceso a datos restringidos

El acceso a las grabaciones de audio originales o a las transcripciones literales para validación científica requiere autorización explícita. El procedimiento es:

1. **Presentar una solicitud formal por escrito** que justifique el uso previsto, acompañada de un análisis del beneficio para la comunidad.
2. **Alineación con los criterios de la Asamblea** — La propuesta de uso y los beneficios resultantes deben analizarse o ajustarse según las necesidades reales, pertinentes y relevantes de la comunidad.
3. **Autorización de la Asamblea Comunitaria** — La Asamblea conserva el derecho de veto sobre sus datos.
4. **Credenciales temporales** — Una vez aprobada la solicitud, se asignan credenciales temporales y contraseñas de acceso restringido.
5. **Alcance limitado** — El acceso está limitado a un periodo definido y a un conjunto específico de archivos.
6. **Prohibición de redistribución** — Queda prohibida la copia, redistribución o almacenamiento permanente de los archivos fuera del entorno autorizado.
7. **Informe final** — Se debe presentar un informe final del uso de los datos.
8. **Devolución de resultados** — Los resultados deben devolverse en un formato ajustado a la audiencia comunitaria.
9. **Cumplimiento** — Cualquier incumplimiento del protocolo resulta en la revocación inmediata del acceso y posibles acciones legales.

Ver [`../docs/data_governance.md`](../docs/data_governance.md) para el marco completo.

### Contacto

Para solicitudes de acceso a datos o preguntas sobre los datasets distribuidos aquí, por favor contactar a la autora correspondiente del proyecto:

> **Silvia Fernández Sabido** — SECIHTI / CentroGeo, Mérida, Yucatán, México

Las solicitudes de datos restringidos se revisarán con la Asamblea Comunitaria correspondiente. Los tiempos de respuesta dependen de los ritmos propios de la Asamblea.
