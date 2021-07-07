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

INSERT INTO accessories
VALUES (6, 'Рюкзак', 'A backpack', 'Asics', 'Lightweight Running Backpack', 'РЮКЗАК ASICS УНИСЕКС
100% полиэстер
Легкий, прочный материал с водоотталкивающими свойствами
Объем 10 литров
Регулируемые лямки из сетки
Вместительное центральное отделение
Карман спереди на молнии
Боковые карманы из сетки
Карман на лямке рюкзака
Карман на молнии на поясной застежке
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Ширина(W) 23см х Высота(H) 44см', 'ASICS UNISEX BACKPACK
100% Polyester
Lightweight, durable material with water repellent properties
Volume 10 liters
Adjustable mesh straps
Spacious central compartment
Front zip pocket
Mesh side pockets
Backpack strap pocket
Zip pocket with belt closure
Reflective elements to increase the level of safety of movement in the dark
Width (W) 23cm x Height (H) 44cm', 'Asics_Lightweight_Running_Backpack.jpg', 124.00, 5, 1);

INSERT INTO accessories
VALUES (7, 'Козырек', 'Visor', 'Salomon', 'SENSE VISOR PAPAYA',
 'Козырек Salomon SENSE VISOR PAPAYA защитит ваши глаза от яркого солнца и ветра.',
  'The Salomon SENSE VISOR PAPAYA protects your eyes from bright sun and wind.',
   'Salomon_SENSE_VISOR_PAPAYA.jpg', 94.00, 5, 1);

INSERT INTO accessories
VALUES (8, 'Рюкзак', 'A backpack', 'Mizuno', 'Lightweight Running Backpack', 'РЮКЗАК MIZUNO УНИСЕКС
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
Width (W) 20cm x Height (H) 42cm', 'Mizuno_Running_Backpack_10L_black.jpg', 99.00, 3, 1);

INSERT INTO accessories
VALUES (9, 'Перчатки-варежки', 'Gloves-mittens', 'Reusch', 'Terro Stormbloxx',
 'Эта эластичная и легкая флисовая перчатка, включающая убираемый полностью ветрозащитный капюшон на тыльной стороне ладони, снабжена силиконовым принтом на ладони, светоотражающими элементами и специальной аппликацией на кончике большого и указательного пальцев, которая позволяет использовать мобильные устройства.',
  'Featuring a fully retractable windproof hood on the back of the hand, this elastic and lightweight fleece glove features a silicone print on the palm, reflective detailing and a dedicated thumb and index fingertip appliqué for mobile use.',
   'Reusch_Terro_Stormbloxx.jpg', 94.00, 10, 1);

INSERT INTO accessories
VALUES (10, 'Перчатки', 'Gloves', 'Mizuno', 'Warmalite Glove', 'БЕГОВЫЕ ПЕРЧАТКИ MIZUNO УНИСЕКС
88% полиэстер / 12% эластан
Легкий, ветрозащитный материал с влаговыводящими свойствами
Технология WarmaLite сохраняет тепло при занятиях в условиях низких температур
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'MIZUNO UNISEX RUNNING GLOVES
88% polyester / 12% elastane
Lightweight, windproof material with moisture wicking properties
WarmaLite technology keeps you warm when exercising in low temperatures
Reflective elements to increase the level of safety of movement in the dark', 'Mizuno_Warmalite_Glove.jpg', 79.00, 7, 1);

INSERT INTO accessories
VALUES (11, 'Перчатки', 'Gloves', 'Asics', 'Lite Show Gloves', 'БЕГОВЫЕ ПЕРЧАТКИ ASICS
78% полиэстер / 22% вискоза
Легкий, ветрозащитный материал с влаговыводящими свойствами
Технология Motion Dry позволяет коже свободно дышать, отводит влагу и сохраняет тело в сухости и при этом препятствует образованию неприятного запаха
Специальный материал на подушечках пальцев для сенсорного экрана
Защита от UF-лучей
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'ASICS RUNNING GLOVES
78% polyester / 22% viscose
Lightweight, windproof material with moisture wicking properties
Motion Dry technology allows skin to breathe freely, wicks moisture away and keeps the body dry while preventing unpleasant odors
Dedicated material on the fingertips for the touch screen
UV protection
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Lite_Show_Gloves.jpg', 64.00, 20, 1);

INSERT INTO accessories
VALUES (12, 'Перчатки', 'Gloves', 'Dare2b', 'Cogent Glove', 'БЕГОВЫЕ УТЕПЛЕННЫЕ ПЕРЧАТКИ УНИСЕКС
100% полиэстер
Мягкий материал с влаговыводящими свойствами
Специальный материал на подушечках указательного и большого пальцев для сенсорного экрана
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'UNISEX INSULATED RUNNING GLOVES
100% Polyester
Soft material with moisture wicking properties
Dedicated material on the thumb and forefinger pads for touchscreen
Reflective elements to increase the level of safety of movement in the dark', 'Dare2b_Cogent_Glove.jpg', 64.00, 15, 1);

INSERT INTO accessories
VALUES (13, 'Перчатки', 'Gloves', 'Asics', 'Thermal Gloves', 'БЕГОВЫЕ УТЕПЛЕННЫЕ ПЕРЧАТКИ ASICS УНИСЕКС
100% полиэстер
Мягкий материал с влаговыводящими свойствами
Специальный материал на подушечках указательного и большого пальцев для сенсорного экрана
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток', 'ASICS UNISEX INSULATED RUNNING GLOVES
100% Polyester
Soft material with moisture wicking properties
Dedicated material on the thumb and forefinger pads for touchscreen
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Thermal_Gloves.jpg', 59.00, 20, 1);

INSERT INTO accessories
VALUES (14, 'Перчатки', 'Gloves', 'Swix', 'Tracx', 'Перчатки унисекс самые доступные перчатки в линейке SWIX для беговых лыж.
Качественные замшевые вставки, нетканая высококачественная микрофибра.', 'Unisex gloves are the most affordable gloves in the SWIX range for cross-country skiing.
Quality suede inserts, non-woven high quality microfiber.', 'Swix_Tracx.jpg', 49.00, 12, 1);

INSERT INTO accessories
VALUES (15, 'Сумка на пояс', 'Belt bag', 'Asics', 'Waist Pouch black', 'Сумка на пояс, позволяющая взять с собой на пробежку телефон, гели, ключи и другие важные мелочи. Основное отделение закрывается на молнию. Впереди сквозной карман из эластичной растягивающейся ткани, идеально подходит для хранения трикотажных беговых аксессуаров, например: повязки, баффа, рукавов.

Основа сумки из прочной объемной ткани-сетки рипстоп с отличным воздухообменом. Ремень с замком на карабине можно регулировать по объему талии. Светоотражающие элементы для безопасных тренировок в темное время суток.',
 'A belt bag that allows you to take your phone, gels, keys and other important things with you for a run. The main compartment closes with a zipper. Front side pocket made of elastic stretch fabric, ideal for storing jersey running accessories such as bandage, buff, sleeves.

The base of the bag is made of durable ripstop mesh fabric with excellent breathability. The strap with a carabiner can be adjusted to fit the waist. Reflective elements for safe workouts in the dark.', 'Asics_Waist_Pouch_black.jpg', 74.00, 8, 1);

INSERT INTO accessories
VALUES (16, 'Сумка спортивная', 'Sport bag', 'Mizuno', 'Running Waist Bag blue', 'СПОРТИВНАЯ СУМКА-ПОЯС MIZUNO УНИСЕКС
100% полиэстер
Карман на молнии
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Длина(L) 24см х Высота(H) 10см',
 'MIZUNO UNISEX SPORTS BELT
100% Polyester
Zip pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark
Length (L) 24cm x Height (H) 10cm', 'Mizuno_Running_Waist_Bag_blue.jpg', 64.00, 15, 1);

INSERT INTO accessories
VALUES (17, 'Сумка спортивная', 'Sport bag', 'Mizuno', 'Running Waist Bag black', 'СПОРТИВНАЯ СУМКА-ПОЯС MIZUNO УНИСЕКС
100% полиэстер
Карман на молнии
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток
Длина(L) 24см х Высота(H) 10см',
 'MIZUNO UNISEX SPORTS BELT
100% Polyester
Zip pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark
Length (L) 24cm x Height (H) 10cm', 'Mizuno_Running_Waist_Bag_black.jpg', 64.00, 15, 1);

INSERT INTO accessories
VALUES (18, 'Сумка спортивная', 'Sport bag', 'Asics', 'Waistpack Rose', 'CПОРТИВНАЯ СУМКА-ПОЯС ASICS УНИСЕКС
84% нейлон / 16% спандекс
Карман на молнии для хранения вещей
Регулируемый ремень
Светоотражающие элементы для повышения уровня безопасности передвижения в темное время суток',
 'ASICS UNISEX BELT BAG
84% nylon / 16% spandex
Zippered storage pocket
Adjustable strap
Reflective elements to increase the level of safety of movement in the dark', 'Asics_Waistpack_Rose.jpg', 39.00, 8, 1);

INSERT INTO accessories
VALUES (19, 'Кепка', 'Cap', 'Regatta', 'Nautical Blu', 'Кепка Regatta для бега
100% полиэстер, регулировка - нейлоновая лента с PVC зажимом',
 'Regatta Running Cap
100% polyester, adjustable - nylon tape with PVC clip', 'Regatta_Nautical_Blu.jpg', 29.00, 8, 1);

INSERT INTO accessories
VALUES (20, 'Кепка', 'Cap', 'Atlantis', 'Runner', '', '', 'Atlantis_Runner.jpg', 24.00, 10, 1);

INSERT INTO accessories
VALUES (21, 'Очки спортивные', 'Sports glasses', 'XLC', 'Sulawesi SG-C10', 'Солнцезащитные очки Sulawesi SG-C10
Мягкая, гибкая оправа, с двумя сменными линзами, в прозрачных и очень легких оранжевых прорезиненных носовых накладках, в том числе из микрофибры, 100% защита от ультрафиолетовых лучей.',
 'Sulawesi SG-C10 sunglasses
Soft, flexible frame, with two interchangeable lenses, in transparent and very light orange rubberized nose pads, including microfiber, 100% UV protection.',
 'XLC_Sulawesi_SG-C10.jpg', 64.00, 6, 1);

INSERT INTO accessories
VALUES (22, 'Очки', 'Glasses', 'KV+', 'Vertical glasses', 'Спортивные очки KV+  отлично подходят для спорта. Они защищают от ультрафиолетового излучения на 100%, имеют небьющиеся поликарбонатные линзы и регулируемую переносицу.

Радужные (неполяризованные) линзы CW36 изготовлены из ударопрочного поликарбоната, который до 10 раз прочнее обычного пластика или стекла.
Линзы KV + имеют рейтинг UV400 и эффективно защищают ваши глаза от вредных лучей UVA и UVB.
Очки KV + Vertical оснащены регулируемыми носоупорами.
Материал оправы: Grilamid TR90
Все очки KV + сертифицированы CE
К очкам KV + Vertical прилагается мягкий чехол из микроволокна (без жесткого футляра).',
 'The KV + sports glasses are great for sports. They offer 100% UV protection, shatterproof polycarbonate lenses and an adjustable nose bridge.

The CW36 rainbow (non-polarized) lenses are made of impact-resistant polycarbonate, which is up to 10 times stronger than conventional plastic or glass.
KV + lenses are UV400 rated and effectively protect your eyes from harmful UVA and UVB rays.
KV + Vertical goggles are equipped with adjustable nose pads.
Frame Material: Grilamid TR90
All KV + glasses are CE certified
The KV + Vertical goggles come with a soft microfiber case (no hard case).',
 'KV+_Vertical_glasses.jpg', 79.00, 6, 1);