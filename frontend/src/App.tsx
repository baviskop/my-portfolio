import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import Home from './pages/Home';
import Projects from './pages/Projects';
import Blog from './pages/Blog';
import Login from './pages/Login';
import AdminDashboard from './pages/admin/AdminDashboard';
import ProjectManagement from './pages/admin/ProjectManagement';
import BlogManagement from './pages/admin/BlogManagement';
import SkillManagement from './pages/admin/SkillManagement';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="min-h-screen bg-secondary text-white">
          <Routes>
            {/* Public Routes */}
            <Route path="/" element={<Home />} />
            <Route path="/projects" element={<Projects />} />
            <Route path="/blog" element={<Blog />} />
            <Route path="/login" element={<Login />} />

            {/* Admin Protected Routes */}
            <Route 
              path="/admin/*" 
              element={
                <ProtectedRoute adminOnly>
                  <Routes>
                    <Route index element={<AdminDashboard />} />
                    <Route path="projects" element={<ProjectManagement />} />
                    <Route path="blogs" element={<BlogManagement />} />
                    <Route path="skills" element={<SkillManagement />} />
                  </Routes>
                </ProtectedRoute>
              } 
            />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
