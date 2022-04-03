create table User
(
    userId    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    createdAt DATETIME     NOT NULL,
    updatedAt DATETIME         NULL,
    role      VARCHAR(10)  NOT NULL,
    UNIQUE INDEX ux_email (email)
) engine=InnoDB default character set = utf8;