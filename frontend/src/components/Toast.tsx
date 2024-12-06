import React, { useEffect } from 'react';

interface ToastProps {
  message: string;
  onClose: () => void;
}

const Toast: React.FC<ToastProps> = ({ message, onClose }) => {
  useEffect(() => {
    const timer = setTimeout(onClose, 3000); // Duración de 3 segundos
    return () => clearTimeout(timer);
  }, [onClose]);

  return (
    <div className="fixed bottom-8 right-8 bg-green-500 text-white px-6 py-3 rounded-lg shadow-lg flex items-center space-x-3 animate-fade-in-out">
      <span>{message}</span>
      <button onClick={onClose} className="text-white font-bold hover:text-gray-300">
        ✕
      </button>
    </div>
  );
};

export default Toast;
