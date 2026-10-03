# Guía para agentes
## Flujo de trabajo
- Antes de comenzar una tarea, lee AGENTS.md y MEMORY.md.
- Si hay conflicto entre ambos, sigue AGENTS.md.
- Al empezar, lee MEMORY.md para conocer el estado del proyecto y las decisiones tomadas.
- Al terminar una tarea, actualiza MEMORY.md: estado actual, decisiones importantes (con su porqué) y errores a evitar.
- Mantenlo breve (máximo ~50 líneas): resume o elimina lo que ya no aporte.
- Si algo se convierte en una regla permanente, propón moverlo a AGENTS.md en lugar de dejarlo en la memoria.
- No guardes nunca datos sensibles (claves, tokens, datos personales).
- ✅ Siempre: actualizar MEMORY.md al terminar cada tarea.

## Idioma
- Responde en español, salvo que se solicite otro idioma.
- Explica los cambios de forma clara y concisa con ejemplos completos.

## Alcance de los cambios
- Respeta el alcance solicitado y no cambies archivos no relacionados.
- Si se pide orientación sin implementación, explica los pasos sin modificar el código, sugiriendo cuál puede ser el mismo de la implementación.
- Sigue la estructura y las convenciones ya usadas en el proyecto.

## Android y Jetpack Compose
- Mantén las funciones composables enfocadas y reutilizables.
- Coloca los textos de interfaz en `res/values/strings.xml` y usa `stringResource`.
- Conserva el estado de interfaz en el nivel adecuado; no añadas lógica antes de que se solicite.
- Mantén las anotaciones `@Preview` en declaraciones de nivel superior.

## Buenas prácticas de programación
- Divide el código por responsabilidades y usa nombres claros y coherentes.
- Evita duplicar lógica; reutiliza componentes y funciones cuando tenga sentido.
- Mantén las funciones pequeñas, legibles y con una responsabilidad definida.
- Usa tipos explícitos y evita valores nulos o conversiones inseguras cuando sea posible.
- Gestiona los errores de forma clara; no los ocultes con valores predeterminados silenciosos.
- Realiza cambios pequeños y enfocados, sin alterar comportamiento no relacionado.
- Añade comentarios solo cuando aclaren una decisión o lógica no evidente.

## Seguridad y calidad
- Evita permisos, dependencias y accesos a red que no sean necesarios.
- No incluyas credenciales ni datos sensibles en el código.
- Valida los cambios con las comprobaciones existentes cuando se realicen modificaciones de código.
- La comprobación de que la aplicación funciona correctamente se hace viendo la vista previa de los cambios y ejecutando la aplicación en un emulador o dispositivo físico.