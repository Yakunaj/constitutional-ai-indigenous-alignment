# Data governance / Gobernanza de datos

---

## English

### Overview

This document describes the ethical and governance framework that applies to this repository and to the research it accompanies. It operationalizes the **CARE+S principles** (Collective Benefit, Authority to Control, Responsibility, Ethics, plus Digital Sovereignty and Active Participation) and adapts the **Indigenous Governance Protocol for Technology Development and Data Collection** (SECIHTI CBF-2025-G-368) to the specific context of aligning language models with indigenous community knowledge through Constitutional AI.

For the source protocol, see [`protocol_reference.md`](protocol_reference.md).

### Ethical framework: CARE+S

This project follows the **CARE Principles for Indigenous Data Governance**, extended to **CARE+S**:

- **Collective Benefit (C)** — Data and technology must translate into direct benefit for the communities. In this project, that means returning the aligned model, the constitution, and the training methodology to the participating communities, and evaluating the actual usefulness of the resulting system in community contexts.
- **Authority to Control (A)** — The communities retain the inalienable right to determine how, when, and for what purposes their data are processed. This right is exercised through the Community Assembly, which holds decision-making authority, including the power to veto or demand total revocation.
- **Responsibility (R)** — The project team is accountable to the communities. Progress and technical functioning are explained periodically, clearly, and in the communities' own languages.
- **Ethics (E)** — The integrity and well-being of participants take precedence over any technical or academic advance. Systems must process data respecting dignity, safeguarding privacy, and honoring community agreements.
- **+S: Digital Sovereignty and Active Participation** — Communities are not merely consulted; they participate actively in defining the objectives, principles, and evaluation criteria of the system. The long-term goal is for communities to have the technical and infrastructural capacity to store and protect their own data.

### How CARE+S applies to this project

**1. Community co-authorship**
The five community leaders who contributed to the Cultural Constitution are recognized as **co-creators**, not anonymous informants. Their names and communities appear in the paper, in this repository, and in any derived publication. This is a deliberate act against intellectual extractivism.

**2. Assembly as decision-making authority**
Any initial sharing agreement, veto, or request for total revocation and destruction of data falls under the authority of the corresponding **Community Assembly**, operating under its own normative systems. The Assembly's resolution is binding for the project team.

**3. Community oversight committee**
An operational body designated by the Assembly supervises that project files are used only for approved purposes and reports periodically on the state of the data. Its composition is plural and balanced: community members from the territories where data are collected, community members with technical knowledge, and external collaborators aligned with community values.

**4. Right to refusal and revocation**
Any community or individual may refuse documentation of their language, or revoke their authorization at any stage. In case of revocation, the team is obligated to securely delete the corresponding files from all project servers and databases.

**5. Non-commercial use**
Data use for commercial products, trademark registration, paid licensing, or training AI models for private profit is **strictly prohibited**. Indirect benefits derived from the work are distributed equitably to address urgent community needs or to fund self-managed community projects.

**6. Restricted access**
Original audio recordings and transcriptions are classified under **Restricted Access** and are not distributed in this repository. Access for scientific validation requires:

1. A formal written request.
2. An analysis of the benefit for the community.
3. Authorization from the corresponding Community Assembly.
4. Temporary access credentials.
5. Prohibition of redistribution, copying, or permanent storage outside the authorized environment.
6. A final report on the use of the data.
7. Return of results in a format adjusted to the community audience.

See [`../data/README.md`](../data/README.md) for the full procedure.

### Data governance in practice

| Type of material | Location | Who decides |
|---|---|---|
| Cultural Constitution | `constitution/codigo_moral_v2_1.json` | Co-creators + team (published under CC BY-NC-SA 4.0) |
| Prompts and datasets | `prompts/`, `data/` | Community Assembly (restricted access) |
| Training notebooks | `training/` | Team (published under GPLv3) |
| Comparative results | `results/` | Team (published under CC BY-NC-SA 4.0) |
| RAG architecture | `rag/` | Team + community oversight (published under GPLv3) |
| Original audio and transcriptions | Not in this repository | Community Assembly (restricted access) |

### Current limitations

This project has substantive community participation and signed knowledge-sharing agreements. However, it **does not yet count on a formal indigenous council with binding veto power across all stages**. Establishing one — beginning with a consultative council of the current five co-creators and institutional participation from URI — is an explicit goal for future iterations.

We recognize this as a limitation, not as a finished state. The path toward full community governance includes:

1. Consolidating a formal consultative council with the five current co-creators.
2. Expanding the corpus through an open contribution protocol with standardized knowledge-sharing agreements.
3. Developing a participatory cultural evaluation protocol where community members directly validate the system's responses.
4. Establishing formal community governance mechanisms, beginning with the consultative council and moving toward binding veto power.

### References

- Carroll, S. R., et al. (2021). Operationalizing the CARE and FAIR Principles for Indigenous Data Futures. *Scientific Data*, 8(108).
- First Nations Information Governance Centre (2023). *OCAP® Principles*.
- Global Indigenous Data Alliance (GIDA). *CARE Principles for Indigenous Data Governance*.
- Local Contexts. *Traditional Knowledge (TK) Labels*.
- *Protocolo de gobernanza indígena para la elaboración de tecnología y recopilación de datos*. Proyecto SECIHTI CBF-2025-G-368, v1.0, 30 de junio de 2026. See [`protocol_reference.md`](protocol_reference.md).
- Te Hiku Media (2021). *Kaitiakitanga License*.

---

## Español

### Presentación

Este documento describe el marco ético y de gobernanza que aplica a este repositorio y a la investigación que lo acompaña. Operacionaliza los **principios CARE+S** (Beneficio Colectivo, Autoridad para Controlar, Responsabilidad, Ética, más Soberanía Digital y Participación Activa) y adapta el **Protocolo de gobernanza indígena para la elaboración de tecnología y recopilación de datos** (SECIHTI CBF-2025-G-368) al contexto específico de alinear modelos de lenguaje con saberes comunitarios indígenas mediante Constitutional AI.

Para el protocolo fuente, ver [`protocol_reference.md`](protocol_reference.md).

### Marco ético: CARE+S

Este proyecto sigue los **Principios CARE para la Gobernanza de Datos Indígenas**, extendidos a **CARE+S**:

- **Beneficio colectivo (C)** — Los datos y la tecnología deben traducirse en beneficio directo para las comunidades. En este proyecto, eso significa devolver a las comunidades participantes el modelo alineado, la constitución y la metodología de entrenamiento, y evaluar la utilidad real del sistema resultante en contextos comunitarios.
- **Autoridad de control (A)** — Las comunidades conservan el derecho inalienable de determinar cómo, cuándo y para qué se procesan sus datos. Este derecho se ejerce a través de la Asamblea Comunitaria, que tiene autoridad decisoria, incluido el poder de veto o de exigir la revocación total.
- **Responsabilidad (R)** — El equipo del proyecto rinde cuentas ante las comunidades. Los avances y el funcionamiento técnico se explican periódicamente, de forma clara y en las lenguas originarias de las comunidades.
- **Ética (E)** — La integridad y el bienestar de los participantes están por encima de cualquier avance técnico o académico. Los sistemas deben procesar los datos respetando la dignidad, resguardando la privacidad y honrando los acuerdos comunitarios.
- **+S: Soberanía digital y participación activa** — Las comunidades no son solo consultadas; participan activamente en la definición de los objetivos, principios y criterios de evaluación del sistema. El objetivo a largo plazo es que las comunidades cuenten con la capacidad técnica y de infraestructura para almacenar y proteger sus propios datos.

### Cómo se aplica CARE+S a este proyecto

**1. Coautoría comunitaria**
Los cinco líderes comunitarios que contribuyeron a la Constitución Cultural se reconocen como **co-creadores**, no como informantes anónimos. Sus nombres y comunidades aparecen en el artículo, en este repositorio y en cualquier publicación derivada. Esto es un acto deliberado contra el extractivismo intelectual.

**2. La asamblea como autoridad decisoria**
Cualquier acuerdo inicial de compartición, veto o solicitud de revocación total y destrucción de datos recae en la **Asamblea Comunitaria** correspondiente, que actúa bajo sus propios sistemas normativos. La resolución de la Asamblea es vinculante para el equipo del proyecto.

**3. Comité de vigilancia comunitaria**
Un órgano operativo designado por la Asamblea supervisa que los archivos del proyecto se usen solo para los fines aprobados y reporta periódicamente el estado de los datos. Su composición es plural y equilibrada: miembros de las comunidades donde se recopilan los datos, miembros de la comunidad con conocimientos técnicos, y colaboradores externos alineados con los valores comunitarios.

**4. Derecho a la negativa y revocación**
Cualquier comunidad o persona puede negarse a la documentación de su lengua, o revocar su autorización en cualquier etapa. De presentarse una revocación, el equipo tiene la obligación de eliminar de forma segura los archivos correspondientes de todos los servidores y bases de datos del proyecto.

**5. Prohibición de lucro**
Queda **estrictamente prohibido** el uso de los datos para productos comerciales, registro de marcas, licencias de pago o entrenamiento de modelos de IA con fines de lucro privado. Los beneficios indirectos derivados del trabajo se distribuyen equitativamente para cubrir necesidades comunitarias apremiantes o financiar proyectos autogestivos de las propias comunidades.

**6. Acceso restringido**
Las grabaciones de audio originales y sus transcripciones se clasifican bajo la modalidad de **Acceso Restringido** y no se distribuyen en este repositorio. El acceso para validación científica requiere:

1. Solicitud formal por escrito.
2. Análisis del beneficio para la comunidad.
3. Autorización de la Asamblea Comunitaria correspondiente.
4. Credenciales temporales de acceso.
5. Prohibición de redistribución, copia o almacenamiento permanente fuera del entorno autorizado.
6. Informe final del uso de los datos.
7. Devolución de resultados en formato ajustado a la audiencia comunitaria.

Ver [`../data/README.md`](../data/README.md) para el procedimiento completo.

### Gobernanza de datos en la práctica

| Tipo de material | Ubicación | Quién decide |
|---|---|---|
| Constitución Cultural | `constitution/codigo_moral_v2_1.json` | Co-creadores + equipo (publicada bajo CC BY-NC-SA 4.0) |
| Prompts y datasets | `prompts/`, `data/` | Asamblea Comunitaria (acceso restringido) |
| Notebooks de entrenamiento | `training/` | Equipo (publicados bajo GPLv3) |
| Resultados comparativos | `results/` | Equipo (publicados bajo CC BY-NC-SA 4.0) |
| Arquitectura RAG | `rag/` | Equipo + vigilancia comunitaria (publicada bajo GPLv3) |
| Audios originales y transcripciones | No están en este repositorio | Asamblea Comunitaria (acceso restringido) |

### Limitaciones actuales

Este proyecto tiene participación comunitaria sustantiva y acuerdos de compartición de saberes firmados. Sin embargo, **aún no cuenta con un consejo indígena formal con poder de veto vinculante en todas las etapas**. Establecerlo — comenzando por un consejo consultivo con los cinco co-creadores actuales y la participación institucional de URI — es un objetivo explícito para futuras iteraciones.

Reconocemos esto como una limitación, no como un estado acabado. La ruta hacia la gobernanza comunitaria plena incluye:

1. Consolidar un consejo consultivo formal con los cinco co-creadores actuales.
2. Ampliar el corpus mediante un protocolo de contribución abierta con acuerdos de compartición de saberes estandarizados.
3. Desarrollar un protocolo de evaluación cultural participativa donde miembros de las comunidades validen directamente las respuestas del sistema.
4. Establecer mecanismos formales de gobernanza comunitaria, comenzando por el consejo consultivo y avanzando hacia el poder de veto vinculante.

### Referencias

- Carroll, S. R., et al. (2021). Operationalizing the CARE and FAIR Principles for Indigenous Data Futures. *Scientific Data*, 8(108).
- First Nations Information Governance Centre (2023). *OCAP® Principles*.
- Global Indigenous Data Alliance (GIDA). *CARE Principles for Indigenous Data Governance*.
- Local Contexts. *Traditional Knowledge (TK) Labels*.
- *Protocolo de gobernanza indígena para la elaboración de tecnología y recopilación de datos*. Proyecto SECIHTI CBF-2025-G-368, v1.0, 30 de junio de 2026. Ver [`protocol_reference.md`](protocol_reference.md).
- Te Hiku Media (2021). *Kaitiakitanga License*.
