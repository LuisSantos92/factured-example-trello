// Crear el usuario y asignar roles
db.createUser({
    user: "admin",                 // Usuario para la aplicación
    pwd: "administrador1",              // Contraseña del usuario
    roles: [
        {
            role: "readWrite",        // Permiso de lectura y escritura
            db: "tasks"               // Base de datos 'tasks'
        }
    ]
});

// Cambiar a la base de datos 'tasks'
db = db.getSiblingDB("tasks");      // Cambiar a la base de datos 'tasks'

// Insertar algunos datos iniciales
db.my_collection.insertMany([
    { name: "Task 1", status: "Pending" },
    { name: "Task 2", status: "Completed" },
    { name: "Task 3", status: "In Progress" },
    { name: "Task 4", status: "In Progress" }
]);
