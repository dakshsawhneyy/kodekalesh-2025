# Kraftor - AI Content Automation Platform

## Overview
Kraftor is a serverless AI-powered content automation platform designed for fast, structured generation of text and images across multiple domains: social media, education, blogs, marketing, and public outreach.

## The problem
Content creation today is scattered and slow. Whether it’s a teacher preparing notes, a student studying DBMS, or a marketing team planning a campaign, everyone ends up using multiple tools and doing a lot of manual rewriting. Even with AI tools available, the output is usually generic. 
- If I ask for DBMS notes as a student, I don’t get subtopics, MCQs, flashcards, definitions, or a proper roadmap. If someone wants a poster, they don’t get a clean, ready-to-use visual. If a creator wants a caption for Instagram, the output still doesn’t match the tone of the platform.
- Overall, people spend more time fixing what the AI gives rather than using it directly.

## Key Features
- Multi-domain content generation with structured JSON outputs.
- Platform-specific rewriting for Instagram, Twitter, LinkedIn, and YouTube.
- AI-based image generation using Amazon Bedrock and Stable Diffusion XL.
- JWT-based authentication with DynamoDB-backed user management.
- Review and rating system stored in DynamoDB.
- Fully serverless architecture on AWS (Lambda, API Gateway, DynamoDB, S3, Bedrock).

## AWS Architecture 
<img width="1106" height="853" alt="image" src="https://github.com/user-attachments/assets/bba09d80-0431-4a00-b74e-c1d73bc89850" />

## CloudWatch Metrics (Insert Screenshots)
<img width="1843" height="854" alt="7076b404-9d47-4d14-b355-692de3df4cba" src="https://github.com/user-attachments/assets/ad0d5eea-ca21-4db1-be8c-775cd93057af" />

---
<img width="1847" height="863" alt="f1794a35-d2f8-4f4d-9684-21c2b712b4f2" src="https://github.com/user-attachments/assets/c7f2e727-65ee-4560-ab03-e575f3106884" />

---
<img width="1838" height="928" alt="b5565f39-2dd4-4966-a76c-75a0a323a8b1" src="https://github.com/user-attachments/assets/2889a33b-b8d5-4c6a-ad21-b5d7d8db3f73" />

## Backend Endpoints
- POST /generateText – Structured content generation.
- POST /generateImage – Poster-style AI image generation.
- POST /rewriteForPlatform – Platform-native rewriting engine.
- POST /signup – User registration with bcrypt hashing.
- POST /login – JWT-based authentication.
- POST /reviews – Add user reviews.

## Tech Stack
- Backend: AWS Lambda, API Gateway, Bedrock, DynamoDB, S3, CloudWatch.
- Models: Titan Text Express, Titan Image Generator.
- Auth: JWT + bcrypt.
- Frontend: React.

## Deployment
Deployable via AWS Lambda with API Gateway.  
Extended using Terraform for IaC and ECR integration.

## Problem Statement Justification
The platform automates high-volume, cross-context content creation, solving bottlenecks in media, education, marketing, and public outreach. It replaces manual slow workflows with a scalable, serverless, AI-driven system.

## How to Run
Install dependencies and deploy Lambdas via AWS console, SAM, or Serverless Framework.

## Conclusion
Kraftor demonstrates an end-to-end, production-ready AI content system with full cloud integration and structured automation pipelines.
