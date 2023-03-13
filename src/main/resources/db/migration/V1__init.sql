CREATE TABLE students (
  id        bigserial PRIMARY KEY,
  name      VARCHAR(255),
  age       INT
);

INSERT INTO students (name, age) VALUES
('Василий Петров', 18),
('Дмитрий Пушкин', 20),
('Алексей Семёнов', 25),
('Елена Жукова', 33);