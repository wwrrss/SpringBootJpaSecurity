##Synopsis
Ejemplo de un proyecto SpringBoot utilizando JPA + Spring Security, el objetivo es tener un modelo de referencia para mis proyectos. El proyecto tiene una base de datos H2 (in memory) esto puede ser facilmente reemplazado por MySQL o PostgreSQL. La autenticación se realiza consultando los datos de la tabla usuarios, para que esto funcione se creo la clase SecurityConfig que extiende de GlobalAuthenticationConfigurerManager y se sobre escribio el metodo init, en el metodo init se agrega nuestro Custom UserDetailService para que busque de la bd los datos del usuario, tambien aqui se pasa el encoder Bcrypt. 

Ademas de la autenticación se tiene un servicio de autorización para que se pueda autorizar las llamadas al controlador basadas en reglas de negocio, en el HomeController se tiene un metodo para buscar productos por ID, aqui se verifica si el producto solicitado pertenece a la empresa del usuario, si no pertenece responde un 403. 