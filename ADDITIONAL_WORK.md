# Academic work and architecture exploration

Bruno Aguiar · Portfolio summaries · October 2026

These summaries complement [Hercul V2 and Oukio](CASE_STUDIES.md). Source repositories remain private. The review examined repository documentation and file structure; it did not run builds, pipelines or applications. No course materials, source code, credentials, screenshots of internal systems or assessment records are reproduced here.

## DevSecOps — secure delivery pipeline

**Context:** individual academic work in DevSecOps, 2025/2026.

**Problem:** integrate quality and security checks into the delivery workflow for a Java application, so findings are visible before an artifact is published.

The exercise documentation describes a Jenkins pipeline for a Spring Boot application built with Gradle. It combines secret scanning with Gitleaks, policy checks with OPA/Conftest, static analysis with SonarQube, tests and coverage with JaCoCo, dependency analysis with OWASP Dependency-Check, and container scanning with Trivy. Reports and artifacts form part of the documented workflow.

### Engineering discussion

- Check repository content and policy before spending time on the build.
- Distinguish source analysis, dependency analysis and container analysis: each sees a different part of the delivered system.
- Decide which findings should block delivery and how exceptions should be justified and revisited.
- Keep credentials outside pipeline source and consider the privileges granted to build agents.

**Attribution and scope:** the application is an exercise/tutorial base; the portfolio focus is the pipeline integration and its technical report, rather than claiming the underlying application as an original product. The documented controls do not establish that the system is vulnerability-free. Pipeline execution and current tool configuration were not independently verified in this review.

**Interview starting point:** explain one failed check, the report it produces, and the difference between correcting a finding and suppressing it.

## DevOps — containerisation and environment automation

**Context:** academic group work in SWitCH Dev, 2025/2026; completed in a two-person team.

**Problem:** make Java application environments repeatable and understand how packaging decisions affect build and runtime behaviour.

The repository includes exercises on Git, builds, infrastructure automation and containers. The container report documents several Dockerfile approaches for Java chat and Spring applications, image history and runtime resource inspection, a Spring application with a database under Docker Compose, and a comparison with Podman. Other repository material includes an Ansible alternative for environment automation.

### Engineering discussion

- Compare building inside an image with packaging an already-built artifact.
- Explain service discovery, environment configuration and persistent database storage in a multi-container setup.
- Evaluate Docker and Podman against the needs of an exercise rather than assuming one is universally better.
- Document how another developer can recreate and inspect the environment.

**Attribution and scope:** this is team work, not a claim of sole authorship. The public summary describes the shared project scope; individual ownership of tasks is not inferred from the report. Source and course materials remain private. No image-size, performance or reliability improvement is claimed here.

**Interview starting point:** describe the team workflow and identify the specific changes I contributed before discussing implementation details.

## Jarvis V2 — modular assistant exploration

**Context:** personal Java project exploring the architecture of a single-user assistant.

The documented vision covers conversational interaction, memory, voice and task automation. The repository has separate Maven modules for shared contracts, the core, channels, capabilities, inference, persistence and application assembly. Its documentation lists Spring Boot, Spring Modulith and architecture tests using ArchUnit.

### Engineering discussion

The architectural interest is keeping the core independent of concrete channels, inference providers and storage. A shared contract provides a common vocabulary, while application assembly wires the parts together. Explicit module boundaries help expose unwanted dependencies, but also add coordination and abstraction costs.

**Current status:** exploratory. The README still describes an early foundation and contains statements that conflict with the module structure and later commit history. A current feature inventory and runnable demonstration are needed before presenting the assistant as complete. Voice recognition, automation reliability and production readiness are not asserted by this summary.

**Interview starting point:** use a conceptual dependency diagram to explain why provider-specific code should remain outside the core and what an architecture test can enforce.

[Return to profile](https://github.com/bruno1251985)
