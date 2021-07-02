USE orion;

INSERT INTO users
VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        'Александр', 'Артихович', 'art-orient@tut.by', 0, 1);  -- password=admin
INSERT INTO users
VALUES ('natarti', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
        'Наталья', 'Артихович', 'natarti75@gmail.com', 2, 1);  -- password=123456
INSERT INTO users
VALUES ('lesha', 'd17f25ecfbcc7857f7bebea469308be0b2580943e96d13a3ad98a13675c4bfc2',
        'Lesha', 'Artsikhovich', 'art22@tut.by', 2, 1);  -- password=11111


INSERT INTO accessories
VALUES (1, 'Рюкзак', 'A backpack', 'Mizuno', 'Running Backpack 10L', 'РЮКЗАК MIZUNO УНИСЕКС
100% полиэстер
Легкий материал
Мягкие регулируемые лямки
Вместительное центральное отделение на молнии
Карман спереди на молнии
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Ширина(W) 20см х Высота(H) 42см', 'MIZUNO UNISEX BACKPACK
100% Polyester
Lightweight material
Padded adjustable shoulder straps
Spacious central zip compartment
Front zip pocket
Reflective elements to increase the level of safety of movement in the dark
Width (W) 20cm x Height (H) 42cm', 'Mizuno_33GD0018-22.jpg', 99.00, 8, 1);