#version 330

in vec3 out_color;
in vec2 out_uvs;

out vec4 fragColor;

uniform sampler2D sampler;
uniform vec2 cellOffset;

void main() {
    vec4 pixelColor = texture(sampler, out_uvs + cellOffset);

    if (pixelColor.r == 1.0 && pixelColor.g == 0.0 && pixelColor.b == 1.0) {
        fragColor = vec4(0.0, 0.0, 0.0, 1.0);
    } else {
        fragColor = (pixelColor * vec4(out_color, 1.0));
    }
}