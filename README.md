     Статические запросы - не имеют внутри себя параметров
   Динамические запросы - запросы с параметром
   
   
    
  Методы: 
  важно! эти методы за 1 раз один запрос!
    execute() универсальный метод. данный метод может получать несколько ResultSet. Не использовать. Не понял.
    executeUpdate() - с помощью этого метода выполняются INSERT, UPDATE, DELETE. Получать этим методом данные нельзя. ВАЖНО!!!!
    executeQuery() - данный метод для SELECT. Возвращает ResultSet. ВАЖНО!!!!
    
    
    
  Пакетная обработка, это если мы хотим за одну команду execute() выполнить НЕСКОЛЬКО запросов, то можно поместить их всех в 1 пакет
   statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Worms','lives in mallow')");
   statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Crocodile','lives in mallow')");
   statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Snake','lives in mallow')");
   statement.addBatch("INSERT INTO animal(animal_name, animal_description) VALUES ('Bever','lives in mallow')");
   statement.executeBatch(); - для запуска выполнения пакета
   statement.clearBatch(); чтобы очистить пакет и поместить новые запросы
    
    
    метод isClosed() для проверки закрыто ли соединение
    
    
PreparedStatement запросы изначально скомпилированны это дает прирост скорости выполнения