# RAG system / Sistema RAG

---

## English

### Overview

This folder contains the Retrieval-Augmented Generation (RAG) system that connects the culturally aligned language model with a specialized knowledge base on indigenous cosmovision. Its main purpose is to reduce hallucinations and ensure that generated responses are grounded in the retrieved documents, rather than in the model's general training data.

The RAG system retrieves fragments from documents shared by the community co-creators or taken from the URI Latin America and the Caribbean website (urialc.org), which reflects the indigenous cosmovision of several Latin American cultures.

### Repository structure

| Folder | Contents |
|---|---|
| [`backend/`](backend/) | Spring Boot (Java 21) backend: chunking, embeddings, semantic search, prompt building, and Ollama integration |
| [`frontend/`](frontend/) | Angular 19 chat interface for querying the RAG system |

### Architecture

The system follows the standard two-stage RAG architecture:

**Ingestion and vectorization**
1. Documents (PDF) are captured.
2. Text is extracted and split into semantically coherent chunks.
3. Each chunk is transformed into an embedding vector using an embedding model.
4. Vectors and metadata are stored in the database for semantic search.

**Query and generation**
1. The user submits a natural language question.
2. The question is transformed into an embedding using the same model.
3. A cosine similarity search retrieves the most relevant chunks.
4. The retrieved fragments and the original question are combined into a prompt.
5. The language model generates a grounded response.

### Components

**Embedding model** — `Qwen3-Embedding-0.6B`. Open-source model optimized for information retrieval and semantic search. Generates 1024-dimensional vectors. Chosen for its balance between representation quality and low computational requirements, allowing local execution.

**Language model** — The chat interface is powered by the **culturally aligned 1.5B model** obtained through the Constitutional AI pipeline described in this repository (SFT + DPO over Qwen2.5-1.5B-Instruct). Among the three experimental configurations evaluated, this model achieved the highest average RL>SL score (0.523), and it was the largest one the commercial server could support for concurrent inference. The training notebooks and the Cultural Constitution used for alignment are available in [`../training/`](../training/) and [`../constitution/`](../constitution/).

**Inference engine** — Ollama. Provides a unified interface for managing and running both models via a REST API. Decouples the application logic from the specific models, allowing them to be replaced or updated without modifying the general architecture.

**Database** — The system extends an existing relational MySQL model with two entities:
- `semantic_chunks` — stores normalized text fragments and their metadata after segmentation.
- `semantic_embeddings` — stores the vector representation of each chunk.

This decision avoids introducing a dedicated vector database, reducing deployment complexity and resource requirements.

### Deployment

The system was deployed on a commercial server (Hetzner CPX42) with the following stack:

- **Backend** — Spring Boot, served as a JAR via Systemd.
- **Frontend** — Angular static files served by Apache.
- **Database** — MariaDB / MySQL.
- **Reverse proxy** — Apache, routing `/api` to the backend and the rest to the frontend.
- **Inference** — Ollama running locally with the aligned 1.5B model.

### Current status

The commercial server has been **deactivated** due to hosting costs. The code and architecture remain available in this repository for reproducibility and future re-deployment.

Before deactivation, comparative tests were conducted between the aligned 1.5B model and Gemini on questions about indigenous peoples. The aligned model — the best-performing configuration in the paper's evaluation — produced responses closer to the lived reality of indigenous communities, drawing directly on the retrieved documents, while Gemini produced more generic, politically correct answers. The evaluation was exploratory and not systematic: it did not follow a predefined protocol, did not include blind comparison, and did not involve community evaluators.

### Evaluation against Gemini

- [`evaluation_gemini.md`](evaluation_gemini.md) — Summary of the exploratory comparison with Gemini.
- [`evaluation_gemini_annex.md`](evaluation_gemini_annex.md) — Full responses of both systems for all four questions.
- [`evaluation_gemini.xlsx`](evaluation_gemini.xlsx) — Original spreadsheet with the comparative evaluation.

### Related files

- Cultural Constitution: [`../constitution/codigo_moral_v2.1.json`](../constitution/codigo_moral_v2.1.json)
- Training notebooks: [`../training/`](../training/)
- Data governance framework: [`../docs/data_governance.md`](../docs/data_governance.md)
- Quantitative results: [`../results/README.md`](../results/README.md)
- Interview guide: [`../docs/interview_guide.md`](../docs/interview_guide.md)
- Knowledge-sharing agreement: [`../docs/knowledge_sharing_agreement.md`](../docs/knowledge_sharing_agreement.md)
- Qualitative analysis protocol: [`../docs/qualitative_analysis.md`](../docs/qualitative_analysis.md)

---

## Español

### Presentación

Esta carpeta contiene el sistema de Generación Aumentada por Recuperación (RAG) que conecta el modelo de lenguaje alineado culturalmente con una base de conocimiento especializada en cosmovisión indígena. Su propósito principal es reducir las alucinaciones y garantizar que las respuestas generadas estén fundamentadas en los documentos recuperados, en lugar de depender únicamente del entrenamiento general del modelo.

El sistema RAG recupera fragmentos de documentos compartidos por los co-creadores comunitarios o tomados del sitio de URI Latinoamérica y el Caribe (urialc.org), que refleja la cosmovisión indígena de varias culturas de América Latina.

### Estructura del repositorio

| Carpeta | Contenido |
|---|---|
| [`backend/`](backend/) | Backend en Spring Boot (Java 21): chunking, embeddings, búsqueda semántica, construcción de prompts e integración con Ollama |
| [`frontend/`](frontend/) | Interfaz de chat en Angular 19 para consultar el sistema RAG |

### Arquitectura

El sistema sigue la arquitectura estándar de dos etapas del RAG:

**Ingesta y vectorización**
1. Se capturan los documentos (PDF).
2. Se extrae el texto y se divide en fragmentos semánticamente coherentes.
3. Cada fragmento se transforma en un vector de embedding usando un modelo especializado.
4. Los vectores y sus metadatos se almacenan en la base de datos para búsqueda semántica.

**Consulta y generación**
1. El usuario envía una pregunta en lenguaje natural.
2. La pregunta se transforma en un embedding con el mismo modelo.
3. Se ejecuta una búsqueda por similitud del coseno para recuperar los fragmentos más relevantes.
4. Los fragmentos recuperados y la pregunta original se combinan en un prompt.
5. El modelo de lenguaje genera una respuesta fundamentada.

### Componentes

**Modelo de embeddings** — `Qwen3-Embedding-0.6B`. Modelo de código abierto optimizado para recuperación de información y búsqueda semántica. Genera vectores de 1024 dimensiones. Se eligió por su equilibrio entre calidad de representación y bajos requerimientos computacionales, permitiendo su ejecución local.

**Modelo de lenguaje** — La interfaz de chat funciona con el **modelo alineado de 1.5B** obtenido mediante el pipeline de Constitutional AI descrito en este repositorio (SFT + DPO sobre Qwen2.5-1.5B-Instruct). Entre las tres configuraciones experimentales evaluadas, este modelo obtuvo el promedio RL>SL más alto (0.523) y era el más grande que el servidor comercial podía soportar para inferencia concurrente. Los notebooks de entrenamiento y la Constitución Cultural usados para la alineación están disponibles en [`../training/`](../training/) y [`../constitution/`](../constitution/).

**Motor de inferencia** — Ollama. Proporciona una interfaz unificada para administrar y ejecutar ambos modelos mediante una API REST. Desacopla la lógica de la aplicación de los modelos concretos, permitiendo sustituirlos o actualizarlos sin modificar la arquitectura general.

**Base de datos** — El sistema extiende un modelo relacional existente en MySQL con dos entidades:
- `semantic_chunks` — almacena los fragmentos de texto normalizados y su metainformación tras la segmentación.
- `semantic_embeddings` — almacena la representación vectorial de cada fragmento.

Esta decisión evita introducir una base de datos vectorial dedicada, reduciendo la complejidad del despliegue y los recursos necesarios.

### Despliegue

El sistema se desplegó en un servidor comercial (Hetzner CPX42) con el siguiente stack:

- **Backend** — Spring Boot, servido como JAR mediante Systemd.
- **Frontend** — Archivos estáticos de Angular servidos por Apache.
- **Base de datos** — MariaDB / MySQL.
- **Proxy inverso** — Apache, enrutando `/api` al backend y el resto al frontend.
- **Inferencia** — Ollama corriendo localmente con el modelo alineado de 1.5B.

### Estado actual

El servidor comercial ha sido **desactivado** por costos de hospedaje. El código y la arquitectura permanecen disponibles en este repositorio para reproducibilidad y futuro redespliegue.

Antes de la desactivación, se realizaron pruebas comparativas entre el modelo alineado de 1.5B y Gemini sobre preguntas acerca de pueblos indígenas. El modelo alineado —la configuración con mejor desempeño en la evaluación del paper— produjo respuestas más cercanas a la realidad vivida por las comunidades indígenas, tomando directamente de los documentos recuperados, mientras que Gemini produjo respuestas más genéricas y políticamente correctas. La evaluación fue exploratoria y no sistemática: no siguió un protocolo predefinido, no incluyó comparación ciega ni contó con evaluadores comunitarios.

### Evaluación contra Gemini

- [`evaluation_gemini.md`](evaluation_gemini.md) — Resumen de la comparación exploratoria con Gemini.
- [`evaluation_gemini_annex.md`](evaluation_gemini_annex.md) — Respuestas completas de ambos sistemas para las cuatro preguntas.
- [`evaluation_gemini.xlsx`](evaluation_gemini.xlsx) — Hoja de cálculo original con la evaluación comparativa.

### Archivos relacionados

- Constitución Cultural: [`../constitution/codigo_moral_v2.1.json`](../constitution/codigo_moral_v2.1.json)
- Notebooks de entrenamiento: [`../training/`](../training/)
- Marco de gobernanza de datos: [`../docs/data_governance.md`](../docs/data_governance.md)
- Resultados cuantitativos: [`../results/README.md`](../results/README.md)
- Guía de entrevista: [`../docs/interview_guide.md`](../docs/interview_guide.md)
- Acuerdo de compartición de saberes: [`../docs/knowledge_sharing_agreement.md`](../docs/knowledge_sharing_agreement.md)
- Protocolo de análisis cualitativo: [`../docs/qualitative_analysis.md`](../docs/qualitative_analysis.md)
