# Notebooks de entrenamiento

## Descripción general

Esta carpeta contiene los tres notebooks usados para entrenar y 
evaluar los modelos alineados:

| Notebook | Fase |
|---|---|
| `sft_qwen_cai.ipynb` | Ajuste supervisado (SFT) con el loop de crítica y revisión constitucional |
| `dpo_qwen_cai.ipynb` | Optimización directa de preferencias (DPO) con 24 pares de preferencias |
| `evaluation_qwen.ipynb` | Evaluación comparativa: Qwen base vs SL-CAI vs RL-CAI |

## Configuraciones experimentales

El pipeline de entrenamiento se ejecutó en tres configuraciones experimentales:

| Config | Modelo base | Packing de secuencias | Promedio RL>SL |
|---|---|---|---|
| A | Qwen2.5-7B-Instruct | sí | 0.324 |
| B | Qwen2.5-1.5B-Instruct | sí | 0.523 |
| C | Qwen2.5-7B-Instruct | no | 0.474 |

Las configuraciones A y C comparten el mismo modelo base y, por lo 
tanto, los mismos datasets; difieren solo en el uso del packing de 
secuencias durante el entrenamiento DPO. La configuración B usa 
Qwen2.5-1.5B-Instruct y sus propios datasets.

## Datasets

Esta carpeta también contiene los datasets generados y usados durante 
el entrenamiento:

| Archivo | Modelo base | Fase | Contenido |
|---|---|---|---|
| `dataset_sft_cultural_1.5B.jsonl` | Qwen2.5-1.5B | SFT | Prompt, respuesta inicial y respuesta revisada |
| `dataset_sft_cultural_7B.jsonl` | Qwen2.5-7B | SFT | Misma estructura |
| `preference_dataset_1.5B.jsonl` | Qwen2.5-1.5B | DPO | 24 pares de preferencias (`chosen` / `rejected`) |
| `preference_dataset_7B.jsonl` | Qwen2.5-7B | DPO | Misma estructura |

Los datasets de preferencias son transformaciones directas de las 
situaciones concretas en `codigo_moral_v2.1.json`, donde cada situación 
proporciona una respuesta alineada y una desalineada para el mismo 
prompt. Los datasets de SFT dependen de las respuestas iniciales 
generadas por cada modelo base y no pueden reconstruirse sin volver a 
ejecutar el pipeline.

## Nota sobre rutas

Estos notebooks fueron desarrollados y ejecutados en Google Colab con 
Google Drive montado. Algunas rutas son absolutas y apuntan al entorno 
de desarrollo original, incluido un enlace directo al archivo de la 
Constitución Cultural alojado en el Drive de una colaboradora. Si 
clonas este repositorio y quieres ejecutar los notebooks, ajusta esas 
rutas a tu propio entorno. El resto del código es portable.

## Entorno

- Google Colab con GPU T4
- Qwen2.5-7B-Instruct y Qwen2.5-1.5B-Instruct
- Unsloth, TRL, PEFT, bitsandbytes

## Resultados

Los resultados comparativos de las tres configuraciones están en 
[`../results/`](../results/).

## Gobernanza de datos

Las grabaciones de audio y transcripciones originales de las entrevistas 
comunitarias no se distribuyen en este repositorio. Su uso se rige por 
el protocolo de gobernanza indígena del proyecto (SECIHTI CBF-2025-G-368), 
que las clasifica como acceso restringido. Ver 
[`../data/README.md`](../data/README.md) para más detalles.

Los datasets en esta carpeta se derivan de la constitución cultural y 
no contienen datos crudos de las entrevistas. Se distribuyen bajo la 
misma licencia que los artefactos científicos (CC BY-NC-SA 4.0).
