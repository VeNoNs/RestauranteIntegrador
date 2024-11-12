// mozo/liberarmesa/layout.tsx
'use client';
import "@/styles/globals.css";
import Sidebar from '@/components/mozo/sidebar'; // Asegúrate de que la ruta sea correcta
import React, { ReactNode } from 'react';

interface MozoLayoutProps {
  children: ReactNode;
}

const MozoLayout: React.FC<MozoLayoutProps> = ({ children }) => {
  return (
    <div className="flex h-screen">
      <Sidebar />
      <div className="flex-1 p-8 bg-gray-50">{children}</div>
    </div>
  );
};

export default MozoLayout;
