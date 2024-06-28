import ReactDOM from 'react-dom/client'
import { BrowserRouter as Router } from 'react-router-dom';
import ThemeProvider from './utils/ThemeContext.tsx';
import App from './App.tsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')!).render(
    <Router>
        <ThemeProvider>
            <App />
        </ThemeProvider>
    </Router>
)