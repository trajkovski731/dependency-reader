<template>
    <div>
      <div v-for="(dep, i) in dependencies" :key="i">
        {{dep.name}} {{dep.value}}
      </div>
    </div>
</template>

<script>
  export default {
    name: 'Dependencies',
    props: {
      instance: { //<1>
        type: Object,
        required: true
      },
      dependencies: []
    },
    async created() {
      const { artifact, version } = this.instance.info.build
      this.instance.axios.get(`https://dependency-reader.herokuapp.com/${artifact}/${version}`).then(res => {
        this.dependencies = res.data;
      })//<2>
    }
  }
</script>

<style scoped>

</style>
