module.exports= function(config){
  config.set({
    frameworks : ['jasmine'],
    files: [
      'app.component.ts',
      'app.component.spec.ts',
      'farm.ts',
      'farm.service.ts',
      'farm.service.spec.ts'
    ],
    plugins : ['karma-jasmine'],
    reporters: ['dots'],
    colors: true,
    singleRun: false
  })
}