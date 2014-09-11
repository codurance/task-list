module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        tsd: {
            refresh: {
                options: {
                    command: 'reinstall',
                    config: 'tsd.json'
                }
            }
        },
        typescript: {
            base: {
                src: ['src/*.ts', 'tests/*.ts'],
                options: {
                    module: 'commonjs',
                    sourceMap: true,
                    target: 'ES5'
                }
            }
        },
        nodeunit: {
            all: ['tests']
        }
    });
    grunt.loadNpmTasks('grunt-tsd');
    grunt.loadNpmTasks('grunt-typescript');
    grunt.loadNpmTasks('grunt-contrib-nodeunit');

    grunt.registerTask('default', ['tsd', 'typescript', 'nodeunit']);
};