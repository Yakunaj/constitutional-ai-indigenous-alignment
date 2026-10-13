# Training notebooks

## Overview

This folder contains the three notebooks used to train and evaluate 
the aligned models:

| Notebook | Phase |
|---|---|
| `sft_qwen_cai.ipynb` | Supervised Fine-Tuning (SFT) with the constitutional critique/revision loop |
| `dpo_qwen_cai.ipynb` | Direct Preference Optimization (DPO) with 24 preference pairs |
| `evaluation_qwen.ipynb` | Comparative evaluation: Qwen base vs SL-CAI vs RL-CAI |

## Note on paths

These notebooks were developed and run in Google Colab with Google 
Drive mounted. Some paths are absolute and point to the original 
development environment, including a direct link to the Cultural 
Constitution file hosted in a collaborator's Drive. If you clone 
this repository and want to run the notebooks, adjust those paths 
to your own setup. The rest of the code is portable.

## Environment

- Google Colab with T4 GPU
- Qwen2.5-7B-Instruct and Qwen2.5-1.5B-Instruct
- Unsloth, TRL, PEFT, bitsandbytes

## Results

The comparative results across the three experimental configurations 
(A: 7B with packing; B: 1.5B full; C: 7B without packing) are in 
[`../results/`](../results/).

## Data

The datasets used for training are not distributed in this repository. 
See [`../data/README.md`](../data/README.md) for details.
