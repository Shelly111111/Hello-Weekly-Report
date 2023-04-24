import { defineConfig } from "vite";
import { resolve } from "path";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": resolve(__dirname, "src"), // 路径别名
    },
    extensions: [".js", ".jsx", ".json"],
  },
  server: {
    proxy: {
      "/api_login": {
        target: 'http://10.162.32.129:8080/',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api_login/, ""),
      },
      "/api": {
        target: 'http://10.162.32.129:8088/',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },

    },
  },
});




