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
Width (W) 20cm x Height (H) 42cm', 'Mizuno_Running_Backpack_10L.jpg', 99.00, 7, 1);

INSERT INTO accessories
VALUES (2, 'Сумка спортивная', 'Sport bag', 'Mizuno', 'Running Waist Bottle Bag', 'СПОРТИВНАЯ СУМКА-ПОЯС MIZUNO УНИСЕКС
100% полиэстер
Карман на молнии
Карман для бутылки
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Длина(L) 32см х Высота(H) 18см', 'MIZUNO UNISEX SPORTS BELT
100% Polyester
Zip pocket
Bottle pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark
Length (L) 32cm x Height (H) 18cm', 'Mizuno_Running_Waist_Bottle_Bag.jpg', 84.00, 4, 1);

INSERT INTO accessories
VALUES (3, 'Поясная сумка для бега', 'Running belt bag', 'Asics', 'WAIST POUCH', 'Сумка на пояс, позволяющая взять с собой на пробежку телефон, гели, ключи и другие важные мелочи. Основное отделение закрывается на молнию. Впереди сквозной карман из эластичной растягивающейся ткани, идеально подходит для хранения трикотажных беговых аксессуаров, например: повязки, баффа, рукавов.

Основа сумки из прочной объемной ткани-сетки рипстоп с отличным воздухообменом. Ремень с замком на карабине можно регулировать по объему талии. Светоотражающие элементы для безопасных тренировок в темное время суток.', 'A belt bag that allows you to take your phone, gels, keys and other important things with you for a run. The main compartment closes with a zipper. Front side pocket made of elastic stretch fabric, ideal for storing jersey running accessories such as bandage, buff, sleeves.

The base of the bag is made of durable ripstop mesh fabric with excellent breathability. The carabiner strap can be adjusted to fit the waist. Reflective elements for safe workouts in the dark. ', 'Asics_WAIST_POUCH.jpg', 39.00, 3, 1);

INSERT INTO accessories
VALUES (4, 'Бейсболка', 'Baseball cap', 'Asics', 'Lightweight Running Cap', 'БЕЙСБОЛКА ASICS УНИСЕКС
56% полиэстер / 32% нейлон / 12% спандекс
Легкий, воздухопроницаемый материал с влаговыводящими свойствами
Ремешок для регулировки размера
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'ASICS UNISEX BASEBALL CAP
56% polyester / 32% nylon / 12% spandex
Lightweight, breathable material with moisture wicking properties
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Lightweight_Running_Cap.jpg', 54.00, 10, 1);

INSERT INTO accessories
VALUES (5, 'Ультралегкий козырек', 'Ultra light visor', 'Compressport', 'VISOR white', 'Ультралегкий козырек Compressport Spiderweb идеально подходит для бегунов и триатлетов. Козырек защитит ваши глаза от яркого солнца, ветра, а его мягкая, воздухопроницаемая ткань впитает влагу/пот и быстро высохнет.

• материал: 60% полиэстер. 30% хлопок, 10% эластан;
• подкладка в области лба из сетчатого материала с антибактериальной обработкой;
• удобная посадка благодаря эластичной, бесшовной конструкции;
• жесткий козырек.', 'The ultra-light Compressport Spiderweb visor is ideal for runners and triathletes. The visor will protect your eyes from the bright sun, wind, and its soft, breathable fabric wicks moisture / sweat and dries quickly.

• material: 60% polyester. 30% cotton, 10% elastane;
• lining in the forehead area made of mesh material with antibacterial treatment;
• comfortable fit thanks to the elastic, seamless construction;
• rigid visor.', 'Compressport_VISOR_white.jpg', 69.00, 2, 1);