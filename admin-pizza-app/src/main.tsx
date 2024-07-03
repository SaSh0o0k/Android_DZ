import { store } from "app/store.ts";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { BrowserRouter as Router } from "react-router-dom";
import ThemeProvider from "utils/contexts/ThemeContext.tsx";

import App from "./App.tsx";
import "./css/index.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
    <Provider store={store}>
      <Router>
        <ThemeProvider>
          <App />
        </ThemeProvider>
      </Router>
    </Provider>,
);
