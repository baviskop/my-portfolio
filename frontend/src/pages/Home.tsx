import React from 'react';
import { motion } from 'framer-motion';
import { ArrowRight, Code, BookOpen, Send, Mail } from 'lucide-react';
import { Link } from 'react-router-dom';
import Layout from '../components/Layout';

const Home = () => {
  return (
    <Layout>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        {/* Hero Section */}
        <section className="py-20 md:py-32 flex flex-col items-center text-center">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5 }}
          >
            <h1 className="text-5xl md:text-7xl font-extrabold mb-6 bg-gradient-to-r from-primary to-accent bg-clip-text text-transparent">
              Building Digital Experiences
            </h1>
            <p className="text-xl text-slate-400 max-w-2xl mb-10">
              Full-stack developer passionate about creating elegant solutions to complex problems. 
              Specializing in Spring Boot and React.
            </p>
            <div className="flex flex-wrap justify-center gap-4">
              <Link 
                to="/projects" 
                className="bg-primary hover:bg-blue-600 px-8 py-3 rounded-full font-semibold flex items-center gap-2 transition-all"
              >
                View Projects <ArrowRight size={20} />
              </Link>
              <Link 
                to="/blog" 
                className="bg-white/5 hover:bg-white/10 px-8 py-3 rounded-full font-semibold transition-all border border-white/10"
              >
                Read Blog
              </Link>
            </div>
          </motion.div>
        </section>

        {/* Features Section */}
        <section className="py-20 grid md:grid-cols-3 gap-8">
          {[
            { 
              title: 'Clean Code', 
              desc: 'Writing maintainable, scalable, and efficient code is my top priority.',
              icon: <Code className="text-primary" size={32} />
            },
            { 
              title: 'Continuous Learning', 
              desc: 'Always exploring new technologies and best practices in software engineering.',
              icon: <BookOpen className="text-accent" size={32} />
            },
            { 
              title: 'Collaboration', 
              desc: 'Effective communication and teamwork to achieve project goals.',
              icon: <Send className="text-green-500" size={32} />
            }
          ].map((feature, i) => (
            <motion.div
              key={i}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              transition={{ delay: i * 0.1 }}
              viewport={{ once: true }}
              className="p-8 bg-white/5 rounded-2xl border border-white/10 hover:border-primary/50 transition-colors"
            >
              <div className="mb-4">{feature.icon}</div>
              <h3 className="text-xl font-bold mb-2">{feature.title}</h3>
              <p className="text-slate-400">{feature.desc}</p>
            </motion.div>
          ))}
        </section>

        {/* Contact CTA */}
        <section className="py-20 text-center">
          <div className="bg-gradient-to-r from-primary/20 to-accent/20 p-12 rounded-3xl border border-white/10">
            <h2 className="text-3xl font-bold mb-4">Have a project in mind?</h2>
            <p className="text-slate-400 mb-8 max-w-xl mx-auto">
              I'm currently available for freelance work and full-time opportunities. 
              Let's build something amazing together.
            </p>
            <a 
              href="mailto:your.email@example.com" 
              className="inline-flex items-center gap-2 bg-white text-slate-900 px-8 py-3 rounded-full font-bold hover:bg-slate-200 transition-all"
            >
              Get In Touch <Mail size={20} />
            </a>
          </div>
        </section>
      </div>
    </Layout>
  );
};

export default Home;
