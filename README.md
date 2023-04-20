# Spring-Boot-Rest-Api---Blog-Application
Building REST API’s using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker & Deploy on AWS
<h1>High level requirements for Blog App</h1>
<ul>
    <li><b>Post Managements</b> - Create, Read, Update, Delete Posts. Provide Paginatio and Sorting Support.</li>
    <li><b>Post Managements</b> - Create, Read, Update, Delete Comments for Blog Post.</li>
    <li><b>Authentication and Authorization</b> - Register, Login and Security</li>
    <li><b>Authentication and Authorization</b> - Create, Read, Update and Delete Category.</li>
</ul>
<h3>Post Management</h3>
<p><i>Build the REST APIs for Post resource</i></p>
<ol>
    <li>Create Post REST API : "/posts"</li>
    <li>Get Single Post REST API : "/posts/{id}"</li>
    <li>Get All Posts REST API : "/posts"</li>
    <li>Get All Posts REST API with Pagination and Sorting : "/posts?pageNo=&pageSize=&sortField=&orderBy="</li>
    <li>Update Post REST API : "/posts/{id}"</li>
    <li>Delete Post REST API : "/posts/{id}"</li>
</ol>
<h3>Comments Management for Posts</h3>
<p><i>Build the REST APIs for Comment resource(one to many mapping.)</i></p>
<ol>
    <li>Create Comment REST API : "/posts/{post_id}/comments"</li>
    <li>Get Single Comment REST API : "/posts/{post_id}/comments/{comment_id}"</li>
    <li>Get All Comments REST API : "/posts/{post_id}/comments"</li>
    <li>Update Comment REST API : "/posts/{post_id}/comments/{comment_id}"</li>
    <li>Delete Comment REST API : "/posts/{post_id}/comments/{comment_id}"</li>
</ol>
<h3>Exception handling and validations</h3>
<ol>
    <li>Handle the exceptuons and errors and return proper error response to the client</li>
    <li>Validate the RESP API request and send the validation error response to the client</li>
</ol>
<h3>Securing REST APIs</h3>
<ol>
    <li>Secure REST APIs using DatabaseAuthentication</li>
    <li>Build Login/Signin REST API</li>
    <li>Build Register/Signup REST API</li>
    <li>Use JWT - Json Web Token based Authentication to Secure the REST  APIs</li>
    <li>Implement Role-based security - ADMIN and USER roles</li>
</ol>
<h3>Category Management</h3>
<p><i>Build the REST APIs for Category resource</i></p>
<ol>
    <li>Create Category REST API : "/categories"</li>
    <li>Get Single Category REST API : "/categories/{id}"</li>
    <li>Get All Category REST API : "/categories"</li>
    <li>Update Category REST API : "/categories/{id}"</li>
    <li>Delete Category REST API : "/categories/{id}"</li>
    <li>Get Posts By Category REST API : "/categories/{id}/posts"</li>
</ol>
<h3>Versioning REST APIs and Deploying on AWS Cloud</h3>
<ol>
    <li>Versioning REST APIs using different stratergies</li>
    <li>Deploy Blog app on AWS Cloud</li>
</ol>
