'use client';
import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';

const Sidebar: React.FC = () => {
  const router = useRouter();
  const [usuarioLogueado, setUsuarioLogueado] = useState<string | null>(null);
  const [rolUsuario, setRolUsuario] = useState<string>('Administrador Personal'); // Rol por defecto

  const handleNavigation = (path: string) => {
    router.push(path);
  };

  const handleLogout = () => {
    // Eliminar el usuario del localStorage
    localStorage.removeItem('loggedInUser');
    router.push('/');
  };

  // Lee los datos del usuario logueado desde localStorage al cargar el componente
  useEffect(() => {
    const userData = localStorage.getItem('loggedInUser'); // Nombre del localStorage
    if (userData) {
      const { nombre } = JSON.parse(userData);
      setUsuarioLogueado(nombre);
      // No se establece rol desde localStorage, se usa el valor por defecto
    } else {
      console.log("No hay datos de usuario en localStorage");
    }
  }, []);

  return (
    <aside className="w-64 bg-gray-100 h-full p-6 flex flex-col justify-between">
      <div>
        <h2 className="text-2xl font-bold text-black mb-8">Dish Delight</h2>
        <nav>
          <ul>
            <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/mesas')}
                className="text-gray-600 hover:text-black"
              >
                📋 Mesas
              </button>
            </li>
            <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/productos')}
                className="text-gray-600 hover:text-black"
              >
                🍽️ Productos
              </button>
            </li>
            <li className="mb-6">
              <button
                onClick={() => handleNavigation('/administradorpersonal/empleados')}
                className="text-gray-600 hover:text-black"
              >
                👥 Empleados
              </button>
            </li>
          </ul>
        </nav>
      </div>

      {/* Mostrar quién está logueado y su rol */}
      <div className="mt-8">
        {usuarioLogueado && (
          <>
            <p className="text-black font-bold mb-2">👤 {usuarioLogueado}</p>
            <p className="text-gray-600 mb-4">{rolUsuario}</p> {/* Mostrar rol por defecto */}
          </>
        )}

        {/* Botón de logout */}
        <button
          onClick={handleLogout}
          className="w-full bg-red-500 text-white py-2 px-4 rounded hover:bg-red-600"
        >
          Cerrar Sesión
        </button>
      </div>
    </aside>
  );
};

export default Sidebar;
