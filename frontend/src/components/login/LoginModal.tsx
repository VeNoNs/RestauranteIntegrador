import axios from 'axios';
import { useState } from 'react';

interface LoginModalProps {
  closeModal: () => void; // Definimos el tipo de closeModal
}

const LoginModal: React.FC<LoginModalProps> = ({ closeModal }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  // Definimos el tipo del evento e como React.FormEvent
  const handleLogin = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        email,
        password,
      });

      const roleOrTipoEmpleado = response.data;

      // Manejar las diferentes respuestas
      if (roleOrTipoEmpleado === 'admin_general') {
        window.location.href = '/administradorgeneral';
      } else if (roleOrTipoEmpleado === 'admin_restaurante') {
        window.location.href = '/administradorpersonal';
      } else if (roleOrTipoEmpleado.startsWith('empleado')) {
        // Verificar el tipo de empleado dividiendo el string y convirtiéndolo a minúsculas
        const tipoEmpleado = roleOrTipoEmpleado.split(':')[1].toLowerCase(); // Convertir a minúsculas
        console.log('Tipo de empleado (normalizado):', tipoEmpleado); // Mostrar tipo de empleado normalizado
        if (tipoEmpleado === 'cocina') {
          window.location.href = '/cocina'; // Redirigir a la vista de cocina
        } else if (tipoEmpleado === 'mozo') {
          window.location.href = '/mozo'; // Redirigir a la vista de mozo
        } else {
          setError('Tipo de empleado no reconocido.');
        }
      } else if (roleOrTipoEmpleado === 'comensal') {
        window.location.href = '/cliente';
      } else {
        setError('Rol no reconocido.');
      }
    } catch (error) {
      console.error('Error en autenticación:', error);
      setError('Error de autenticación. Verifique sus credenciales.');
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="relative bg-white rounded-lg p-8 w-96">
        <button
          onClick={closeModal}
          className="absolute top-2 right-2 bg-red-500 text-white rounded-md p-3 hover:bg-red-600"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold mb-4 text-black">Iniciar Sesión</h2>
        <form onSubmit={handleLogin}>
          <input
            type="email"
            placeholder="Correo electrónico"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="border p-2 mb-4 w-full rounded"
          />
          {error && <p className="text-red-500">{error}</p>}
          <button className="bg-orange-500 text-white px-4 py-2 rounded-full w-full hover:bg-orange-600">
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginModal;
