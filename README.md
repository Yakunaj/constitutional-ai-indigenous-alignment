# Constitutional AI for Indigenous Alignment

**Aligning language models with indigenous community knowledge through Constitutional AI.**

[![License: Code GPLv3 · Content CC BY-NC-SA](https://img.shields.io/badge/License-GPLv3%20%7C%20CC%20BY--NC--SA-blue.svg)](#license)
[![Python 3.10+](https://img.shields.io/badge/python-3.10%2B-blue.svg)](https://www.python.org/)
<!--  [![arXiv](https://img.shields.io/badge/arXiv-XXXX.XXXXX-b31b1b.svg)](https://arxiv.org/abs/XXXX.XXXXX)  -->

This repository combines two licensing regimes, consistent with the Indigenous Governance Protocol (SECIHTI CBF-2025-G-368):

- **Code** (notebooks, scripts) — GNU General Public License v3.0 (GPLv3).
  Copyleft: derivative works must remain open.
- **Scientific artifacts** (Cultural Constitution, prompts, documentation) — 
  Creative Commons Attribution-NonCommercial-ShareAlike 4.0 (CC BY-NC-SA 4.0) 
  and Traditional Knowledge (TK) Labels.
- **Datasets and audio recordings** — Not distributed in this repository.
  Access is restricted and requires authorization from the corresponding 
  Community Assembly. See [`LICENSE.md`](LICENSE.md) and [`docs/data_governance.md`](docs/data_governance.md) for details.

> Leer en español: [README.es.md](README.es.md)

---

## Overview

Large language models are predominantly trained on corpora that reflect Western, anglophone, urban middle-class worldviews. This asymmetry is not neutral: when a model answers questions about territory, community authority, reciprocity, or the relationship with nature, it does so from philosophical assumptions that may be alien — or directly opposed — to those of the communities using the system.

This repository accompanies the paper:

> Fernández Sabido, S., Reyes Magaña, J., Chay Tun, J. A., Buenfil, J. C., González Nahuat, V., & Morales, F. (2026). *Alineando modelos de lenguaje con saberes comunitarios indígenas: Constitutional AI para políticas de datos y justicia epistémica desde el Sur Global*. **Texto Livre**, v. 18.

This repository accompanies the following work:

> Fernández Sabido, S., Reyes Magaña, J., Chay Tun, J. A., Buenfil, J. C., 
> González Nahuat, V., & Morales, F. (2026). *Alineando modelos de lenguaje 
> con saberes comunitarios indígenas: Constitutional AI para políticas de datos 
> y justicia epistémica desde el Sur Global* [Aligning language models with 
> indigenous community knowledge: Constitutional AI for data policies and 
> epistemic justice from the Global South]. **Texto Livre**, special issue 
> *AI in Linguistics / Linguistics in AI*. Under review.
> 

It contains the complete system developed to align a language model with the ethical and philosophical principles of five indigenous communities in Latin America, through a **Cultural Constitution** co-created with community leaders and a **Constitutional AI (CAI)** pipeline combining supervised fine-tuning (SFT) and direct preference optimization (DPO), deployed with retrieval-augmented generation (RAG).

## Repository structure

| Folder | Contents |
|---|---|
| [`paper/`](paper/) | Links to the Spanish paper and the English preprint |
| [`constitution/`](constitution/) | The Cultural Constitution: 12 operational principles with traceability to community testimonies |
| [`training/`](training/) | Notebooks for SFT, DPO, and evaluation over Qwen2.5-Instruct with LoRA |
| [`results/`](results/) | Comparative results across four experimental configurations |
| [`rag/`](rag/) | Description of the RAG architecture over the oral corpus *U'uy u t'aan* |
| [`data/`](data/) | Description of the datasets, how to request access, and the knowledge-sharing agreement |
| [`docs/`](docs/) | Language policy, data governance, and the CARE/FAIR framework |

## The Cultural Constitution

The Cultural Constitution is a structured JSON document (`codigo_moral_v2_1.json`) that operationalizes ethical and community principles into a format processable by AI systems. It was built through semi-structured interviews with five indigenous leaders, writers, and defenders — members of URI (United Religions Initiative) — between April and May 2026. They participated not as anonymous informants but as **co-creators**, contributing from their recognized community authority.

The process produced **12 operational principles**:

1. Territorial respect
2. Good living with sufficiency
3. Reciprocity and solidarity
4. Collective decision-making
5. Situated authority
6. Reparation before punishment
7. Hospitality with safeguard
8. Intergenerational transmission
9. Technology with contextual evaluation
10. Consultation and consent
11. Linguistic and translation caution
12. Realism without stigma

Each principle is structured with: name, operational rule, application nuances, supporting interview fragment, concrete situations with aligned and misaligned responses, constitutional critique instructions, and comparative evaluation criteria.

## The CAI pipeline

The training follows the Constitutional AI pipeline in two phases:

- **SFT (Supervised Fine-Tuning)** — The base model generates an initial response; a revision prompt including the original response and the corresponding constitutional principle guides the model to produce a revised response. The revised response, together with aligned examples from the constitution, forms the supervised training dataset.
- **DPO (Direct Preference Optimization)** — 24 pairs of aligned and misaligned responses optimize the model's preferences directly, without a separate reward model.
- **Base model** — Qwen2.5-7B-Instruct and Qwen2.5-1.5B with LoRA adaptation (r=16, α=16, dropout=0.05).
- **Deployment** — A conversational interface with retrieval-augmented generation (RAG) over an oral corpus in Maya and Spanish.

Four experimental configurations were run to estimate robustness:
- (A) 7B with packing
- (B) 1.5B full
- (C) Hybrid: SFT on 1.5B, DPO/evaluation on 7B
- (D) 7B without packing

## Key findings

- The most consistent improvements were observed in **intergenerational transmission**, **linguistic caution**, and **consultation and consent** — principles with clear operational rules and well-defined concrete examples.
- The **1.5B configuration** achieved the highest average RL>SL score (0.523), outperforming 7B configurations. With only 24 preference pairs, model size was not the determining factor for cultural alignment.
- **Sequence packing negatively affected cultural alignment** (0.405 with packing vs. 0.474 without), suggesting that mixing examples from different categories in a single sequence dilutes the learning signal for small datasets.
- **No configuration consistently activated epistemic self-limitation** on generalizing questions, indicating that complex philosophical principles requiring self-restraint need more contrasting negative examples and possibly strategies beyond standard DPO.
- The training did **not** cause catastrophic forgetting: general knowledge questions were answered correctly after SFT and DPO.

## Community co-creators

This work would not have been possible without the generosity and disposition of the five indigenous community members who shared values and practices that are part of their cosmovision:

| Name | People | Country | Role |
|---|---|---|---|
| Don Felipe | Maya | Mexico | Producer, knowledgeable in community norms and land work |
| Francisco Morales | Aymara | Argentina | Indigenous writer, organizer of the Indigenous Writers' Circle in Buenos Aires, cultural activist |
| Julio César Paz Nap | Tolupán | Honduras | Territorial defender, human rights and environmental activist |
| Donatila Girón (Doña Tila) | Lenca | Honduras | Community leader, legal promoter, companion of women victims of violence, defender of land and water |
| Marvin Ramón Castellón | Chorotega | Honduras | Indigenous Chorotega representative of the southern Choluteca zone |

All are members of URI — United Religions Initiative (urialc.org).

## Language policy

This repository is **bilingual by design**. Code, folder names, and metadata are in English for technical interoperability. Scientific artifacts — the Cultural Constitution, training prompts, preference datasets, and evaluation prompts — remain in Spanish, the language in which they were co-created with community leaders. Translating them would reproduce the epistemic asymmetry this work seeks to address.

See [`docs/language_policy.md`](docs/language_policy.md) for details.

## Data governance

This project follows the **CARE Principles for Indigenous Data Governance**  (Collective Benefit, Authority to Control, Responsibility, Ethics) — extended 
in this work to **CARE+S**, adding *Digital Sovereignty and Active Participation* —  alongside the FAIR principles. The governance framework is articulated in the 
**Indigenous Governance Protocol for Technology Development and Data Collection**  (SECIHTI CBF-2025-G-368), adapted to the context of this work.

Key commitments:

- **Community co-authorship** — The five community leaders who contributed to the Cultural Constitution are recognized as co-creators, not anonymous 
  informants. Their names and communities appear in the paper and in this repository.
- **Assembly as decision-making authority** — Any initial sharing agreement, veto, or request for total revocation and destruction of data falls under 
  the authority of the corresponding Community Assembly, operating under its own normative systems.
- **Community oversight committee** — An operational body designated by the Assembly supervises that project files are used only for the approved 
  purposes and reports periodically on the state of the data.
- **Right to refusal and revocation** — Any community or individual may refuse documentation of their language, or revoke their authorization at any stage. 
  In case of revocation, the team is obligated to securely delete the corresponding files from all project servers and databases.
- **Non-commercial use** — Data use for commercial products, trademark registration, paid licensing, or training AI models for private profit is 
  strictly prohibited.
- **Restricted access** — Original audio recordings and transcriptions are classified under *Restricted Access* and are not distributed in this 
  repository. Access for scientific validation requires a formal written request, an analysis of community benefit, and authorization from the 
  corresponding Community Assembly. See [`data/README.md`](data/README.md).

The project does not yet count on a formal indigenous council with binding veto power across all stages; establishing one — beginning with a consultative 
council of the current five co-creators and institutional participation from URI — is an explicit goal for future iterations.

See [`docs/data_governance.md`](docs/data_governance.md) for the full framework and [`docs/protocol_reference.md`](docs/protocol_reference.md) for the source 
protocol.

## Citation

If you use this work, please cite the paper:

```bibtex
@article{fernandezsabido2026constitutional,
  title   = {Alineando modelos de lenguaje con saberes comunitarios indígenas:
             Constitutional AI para políticas de datos y justicia epistémica
             desde el Sur Global},
  author  = {Fernández Sabido, Silvia and Reyes Magaña, Jorge and
             Chay Tun, Jesús Adrián and Buenfil, Jean Carlos and
             González Nahuat, Victor and Morales, Francisco},
  journal = {Texto Livre},
  volume  = {18},
  year    = {2026},
  doi     = {10.1590/1983-3652.yyyy.nnnnn}
}
