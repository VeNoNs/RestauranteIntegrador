'use client';
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';

interface LoginModalProps {
  closeModal: () => void;
}

const LoginModal: React.FC<LoginModalProps> = ({ closeModal }) => {
  const router = useRouter();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    // Obtener los datos del usuario desde localStorage
    const usuarioGuardado = localStorage.getItem('usuario');
    if (!usuarioGuardado) {
      alert('Usuario no registrado.');
      return;
    }

    const { correo, contrasena } = JSON.parse(usuarioGuardado);

    // Verificar correo y contraseña
    if (email === correo && password === contrasena) {
      // Redirigir según el correo
      if (email.includes('@empleadomozo')) {
        router.push('/mozo');
      } else if (email.includes('@administrador')) {
        router.push('/administradorpersonal');
      } else if (email.includes('@admingeneral')) {
        router.push('/administradorgeneral');
      } else if (email.includes('@empleadococina')) {
        router.push('/cocina');
      } else {
        router.push('/');
      }
    } else {
      alert('Correo o contraseña incorrecta.');
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
        <form onSubmit={(e) => e.preventDefault()}>
          <input
            type="email"
            placeholder="Correo electrónico"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="border p-2 mb-4 w-full rounded text-black"
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="border p-2 mb-4 w-full rounded text-black"
          />
          <button
            type="button"
            onClick={handleLogin}
            className="bg-orange-500 text-white px-4 py-2 rounded-full w-full hover:bg-orange-600"
          >
            Entrar
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginModal;
