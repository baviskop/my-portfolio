## Task: Create a Comprehensive AI Prompt for Backend Review and Optimization

I have analyzed your Spring Boot backend and identified several areas for improvement, including typos (e.g., `upadte` in `AdminBlogController`), missing validation logic, and basic exception handling.

### The Prompt You Can Use
You can copy and use this prompt with any advanced AI (like me in a new session) to perform a full audit:

```markdown
Act as a Senior Backend Architect and Security Expert. Please perform a deep audit of my Spring Boot portfolio project with the following goals:

1. **Code Structure & Design Patterns**: Analyze the project layout. Ensure proper separation of concerns between Controllers, Services, Repositories, DTOs, and Entities. Check for redundant code or missing abstractions.
2. **Stability & Error Handling**:
   - Review `GlobalExceptionHandler`. Ensure it handles specific exceptions (e.g., `EntityNotFoundException`, `AccessDeniedException`) with meaningful messages.
   - Check if validation is implemented using `@Valid` and Bean Validation annotations in DTOs.
3. **Bug Hunting**: Scan for typos (e.g., in method names or variable names), logic errors in services, and potential null pointer risks.
4. **Security Audit**:
   - Review `SecurityConfig` and JWT implementation.
   - Ensure Swagger/OpenAPI is configured to support JWT authentication for testing protected endpoints.
5. **Optimization**:
   - Identify inefficient JPA queries (N+1 problems).
   - Suggest better data types or more modern Java features (Java 21).
6. **Clean Code**: Refactor for readability, consistent naming conventions, and removal of unused imports or dead code.

**Instructions**: For every issue found, explain why it's a problem and provide the corrected code snippet. Finally, provide a summary of the architectural health of the project.
```

### Proposed Plan for This Session
If you'd like me to start working on these improvements right now, I have organized the tasks into logical steps:

1. **Phase 1: Stability & Bug Fixes**
   - Fix typos like `upadte` in [AdminBlogController.java](file:///d%3A/_code_long1dep_/25.SU.HSF302/Session06-SpringBoot/my-portfolio/src/main/java/com/long1dep/myportfolio/controller/admin/AdminBlogController.java).
   - Implement Bean Validation (e.g., `@NotBlank`, `@Size`) in all DTOs in the `dto/request` package.
   - Add `@Valid` to all Controller methods receiving `@RequestBody`.

2. **Phase 2: Exception Handling & API Consistency**
   - Enhance [GlobalExceptionHandler.java](file:///d%3A/_code_long1dep_/25.SU.HSF302/Session06-SpringBoot/my-portfolio/src/main/java/com/long1dep/myportfolio/exception/GlobalExceptionHandler.java) to return a structured list of validation errors instead of just the first one.
   - Create custom exceptions (e.g., `ResourceNotFoundException`) and handle them globally.

3. **Phase 3: Swagger & Security Optimization**
   - Configure a `SecurityScheme` in a new OpenAPI configuration file so you can enter your JWT token directly in Swagger UI to test admin endpoints.
   - Review JWT logic for token expiration and refresh handling.

4. **Phase 4: Code Cleaning**
   - Standardize response formats across all controllers.
   - Optimize Service layer logic (e.g., using `Optional` more effectively).

**Would you like me to proceed with Phase 1?**
