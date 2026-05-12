# Submission Review System - COS 730 Assignment 2

This project was built as part of my Software Engineering module at the 
University of Pretoria. The idea was to take a badly designed system, 
implement it as is, analyse what was wrong with it, and then rebuild it 
using proper software engineering practices.

## What the system does

It models an Intelligent Submission and Review System. A researcher 
submits a paper, the system validates it, assigns reviewers, collects 
scores, evaluates the outcome and sends a notification. The final 
decision is either accepted, rejected or needs revision.

## Structure

- `Original/src` - baseline implementation following the original 
sequence diagram exactly, flaws included
- `Optimised/src` - improved version applying GRASP principles, 
proper responsibility assignment and better object interactions

## Built with

Java

## Key improvements in the optimised version

- SubmissionController is no longer a god object
- ReviewerManager handles its own filtering internally
- EvaluationManager owns the full evaluation chain
- Reduced controller-initiated method calls by 71%
